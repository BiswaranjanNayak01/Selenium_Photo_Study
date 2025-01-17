package ETL_Utility;

import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericData;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;
import org.testng.Assert;

import java.io.FileWriter;
import java.io.IOException;

class ParquetToCSVConverter {
    public static void main(String[] args) {
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
}
