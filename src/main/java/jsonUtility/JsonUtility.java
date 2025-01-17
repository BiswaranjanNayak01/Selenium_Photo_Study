package jsonUtility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

public class JsonUtility {
    public static JSONObject modifyJsonPutKeyAndValue(Object obj, String JsonKeyPath, Object newValue) throws ParseException {
        String keypath = JsonKeyPath.replace("[", ",").replace("]", "").replace(".", "/");
        String keys[] = keypath.split("/");
        JSONParser jsonParser = new JSONParser();
        JSONObject object;
        object = (JSONObject) jsonParser.parse(obj.toString());
        JSONObject jsonObject = object;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (key.contains(",")) {
                String KeyName = key.split(",")[0];
                String KeyIndex = key.split(",")[1];
                JSONArray array = new JSONArray();
                array = (JSONArray) jsonObject.get(KeyName);
                jsonObject = (JSONObject) array.get(Integer.parseInt(KeyIndex));
            } else {
                jsonObject = (JSONObject) jsonObject.get(key);
            }
        }
        String key = keys[keys.length - 1];
        jsonObject.put(key, newValue);
        System.out.println(object);
        return object;
    }

    public static Object getJsonKeyValue(Object obj, String JsonKeyPath) throws ParseException {
        String keypath = JsonKeyPath.replace("[", ",").replace("]", "").replace(".", "/");
        String keys[] = keypath.split("/");
        JSONParser jsonParser = new JSONParser();
        JSONObject object;
        object = (JSONObject) jsonParser.parse(obj.toString());
        JSONObject jsonObject = object;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (key.contains(",")) {
                String KeyName = key.split(",")[0];
                String KeyIndex = key.split(",")[1];
                JSONArray array = new JSONArray();
                array = (JSONArray) jsonObject.get(KeyName);
                jsonObject = (JSONObject) array.get(Integer.parseInt(KeyIndex));
            } else {
                jsonObject = (JSONObject) jsonObject.get(key);
            }
        }
        Object value = null;
        String key = keys[keys.length - 1];
        if (key.contains(",")) {
            String KeyName = key.split(",")[0];
            String KeyIndex = key.split(",")[1];
            JSONArray array = new JSONArray();
            array = (JSONArray) jsonObject.get(KeyName);
            value = (JSONObject) array.get(Integer.parseInt(KeyIndex));
        } else {
            if (jsonObject.containsKey(key)) {
                value = jsonObject.get(key);
            } else {
                System.out.println("Key not found");
                Assert.fail();
            }
        }
        System.out.println(value);
        return value;
    }

    public static JSONObject modifyJsonRemoveKey(Object obj, String JsonKeyPath) throws ParseException {
        String keypath = JsonKeyPath.replace("[", ",").replace("]", "").replace(".", "/");
        String keys[] = keypath.split("/");
        JSONParser jsonParser = new JSONParser();
        JSONObject object;
        object = (JSONObject) jsonParser.parse(obj.toString());
        JSONObject jsonObject = object;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (key.contains(",")) {
                String KeyName = key.split(",")[0];
                String KeyIndex = key.split(",")[1];
                JSONArray array = new JSONArray();
                array = (JSONArray) jsonObject.get(KeyName);
                jsonObject = (JSONObject) array.get(Integer.parseInt(KeyIndex));
            } else {
                jsonObject = (JSONObject) jsonObject.get(key);
            }
        }

        String key = keys[keys.length - 1];
        if (jsonObject.containsKey(key)) {
            jsonObject.remove(key);
        } else {
            System.out.println("Key not found");
            Assert.fail();
        }
        System.out.println(object);
        return object;
    }

    public static JSONObject modifyJsonReplaceValue(Object obj, String JsonKeyPath, Object newValue) throws ParseException {
        String keypath = JsonKeyPath.replace("[", ",").replace("]", "").replace(".", "/");
        String keys[] = keypath.split("/");
        JSONParser jsonParser = new JSONParser();
        JSONObject object;
        object = (JSONObject) jsonParser.parse(obj.toString());
        JSONObject jsonObject = object;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (key.contains(",")) {
                String KeyName = key.split(",")[0];
                String KeyIndex = key.split(",")[1];
                JSONArray array = new JSONArray();
                array = (JSONArray) jsonObject.get(KeyName);
                jsonObject = (JSONObject) array.get(Integer.parseInt(KeyIndex));
            } else {
                jsonObject = (JSONObject) jsonObject.get(key);
            }
        }

        String key = keys[keys.length - 1];
        if (jsonObject.containsKey(key)) {
            jsonObject.replace(key, newValue);
        } else {
            System.out.println("Key not found");
            Assert.fail();
        }
        System.out.println(object);
        return object;
    }

    public static void main(String[] args) {
        String name = "name : biswa" +
                " call : me" +
                " name : puspa" +
                " class : ten ";
        System.out.println(name);

    }

}
