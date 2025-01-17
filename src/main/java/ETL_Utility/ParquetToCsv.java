package ETL_Utility;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.Counters;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericData;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;
import org.testng.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ParquetToCSVConverter {
    public static void main1() {
        String inputFilePath = "TestData/ParquetData.parquet";
        String outputFilePath = "TestDataOutput/output.csv";

        try (ParquetReader<GenericRecord> reader = AvroParquetReader.<GenericRecord>builder(new org.apache.hadoop.fs.Path(inputFilePath)).build();
             FileWriter writer = new FileWriter(outputFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            GenericRecord record;
            boolean headerWritten = false;

            while ((record = reader.read()) != null) {
                if (!headerWritten) {
                    // Write headers
                    csvPrinter.printRecord(record.getSchema().getFields().stream()
                            .map(field -> field.name()).toArray());
                    headerWritten = true;
                }

                // Write row data
                GenericRecord finalRecord = record;
                csvPrinter.printRecord(record.getSchema().getFields().stream()
                        .map(field -> finalRecord.get(field.name()))
                        .toArray());
            }
            System.out.println("Parquet file successfully converted to CSV: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error while converting Parquet to CSV: " + e.getMessage());
        }
    }
    public static void main2() throws Exception {
        String parquetFilePath = "path/to/input.parquet";
        String csvFilePath = "path/to/output.csv";

        // Step 3: Read Parquet Data
        ParquetReader<Group> reader = ParquetReader.builder(new GroupReadSupport(), new Path(parquetFilePath)).build();

        Group row;
        List<String[]> records = new ArrayList<>();

        // Step 4: Convert rows to CSV format
        while ((row = reader.read()) != null) {
            SimpleGroup simpleGroup = (SimpleGroup) row;
            String[] record = new String[simpleGroup.getType().getFieldCount()];

            for (int i = 0; i < record.length; i++) {
                record[i] = simpleGroup.getValueToString(i, 0);
            }
            records.add(record);
        }

        // Step 5: Write CSV
        writeToCsv(records, csvFilePath);
    }

    private static void writeToCsv(List<String[]> data, String filePath) throws Exception {
        FileWriter fileWriter = new FileWriter(new File(filePath));
        for (String[] record : data) {
            fileWriter.append(String.join(",", record)).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
    public static void main(String[] args) throws Exception {
        main2();
    }

    }
