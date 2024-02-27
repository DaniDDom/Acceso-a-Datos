package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void getRequest() {
        HttpURLConnection conn = null;

        try {
            URL url = new URL("http://localhost:8080/" + "api-rest/departamentos");

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                Scanner teclado = new Scanner(conn.getInputStream());
                String response = teclado.useDelimiter("\\Z").next();
                teclado.close();

                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    System.out.println(jsonObject.get("depno") + " " + jsonObject.get("nombre"));
                }
            } else {
                System.out.println("Fallo en la conexión.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static void postRequest() throws JSONException {
        HttpURLConnection conn = null;

        String jsonInputString = new JSONObject()
                .put("empno", 1234)
                .put("nombre", "Diez")
                .put("puesto", "Dependiente")
                .put("departamento", new JSONObject()
                        .put("depno",20)
                        .put("nombre","Marketing")
                        .put("ubicacion", "Barcelona"))
                        .toString().toString();

        try {
            URL url = new URL("http://localhost:8080/api-rest/empleados");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input,0, input.length);
            }
            if(conn.getResponseCode() == 200) {
                System.out.println("Empleado Insertado correctamente");
            } else {
                System.out.println("Fallo en la conexión.");
                Scanner teclado = new Scanner(conn.getErrorStream());
                String response = teclado.useDelimiter("\\Z").next();
                teclado.close();

                JSONObject jsonObject = new JSONObject(response).getJSONArray("errors").getJSONObject(0);
                System.out.println(jsonObject.get("defaultMessage"));
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn != null ){
                conn.disconnect();
            }
        }
    }

    public static void deleteRequest(String id) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/api-rest/empleados/" + id );
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            if (conn.getResponseCode() == 200) {
                System.out.println("Empleado Borrado.");
            } else {
                System.out.println("Fallo en la conexión.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn != null){
                conn.disconnect();
            }
        }
    }
    public static void main(String[] args) {
        getRequest();
    }


}
