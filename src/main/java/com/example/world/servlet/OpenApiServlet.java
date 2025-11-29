package com.example.world.servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/openapi.json")
public class OpenApiServlet extends HttpServlet {
    
    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
    
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        setCorsHeaders(response);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        JSONObject openApi = new JSONObject();
        openApi.put("openapi", "3.0.0");
        
        JSONObject info = new JSONObject();
        info.put("title", "WorldApp API");
        info.put("description", "WorldApp RESTful API - Şehirler, Ülkeler ve Ülke Dilleri için CRUD işlemleri");
        info.put("version", "1.0.0");
        openApi.put("info", info);
        
        JSONObject servers = new JSONObject();
        servers.put("url", "http://localhost:8085");
        servers.put("description", "Local development server");
        openApi.put("servers", new org.json.JSONArray().put(servers));
        
        JSONObject paths = new JSONObject();
        
        // Cities endpoints
        paths.put("/cities", createCitiesPaths());
        paths.put("/countries", createCountriesPaths());
        paths.put("/countrylanguages", createCountryLanguagesPaths());
        
        openApi.put("paths", paths);
        
        JSONObject components = new JSONObject();
        JSONObject schemas = new JSONObject();
        schemas.put("City", createCitySchema());
        schemas.put("Country", createCountrySchema());
        schemas.put("CountryLanguage", createCountryLanguageSchema());
        schemas.put("Error", createErrorSchema());
        components.put("schemas", schemas);
        openApi.put("components", components);
        
        out.print(openApi.toString(2));
    }
    
    private JSONObject createCitiesPaths() {
        JSONObject paths = new JSONObject();
        
        // GET /cities
        JSONObject get = new JSONObject();
        get.put("summary", "Şehirleri listele");
        get.put("description", "Tüm şehirleri listeler veya filtreleme parametreleri ile arama yapar");
        get.put("operationId", "getCities");
        org.json.JSONArray getParams = new org.json.JSONArray();
        getParams.put(createParameter("ID", "integer", "Şehir ID'si"));
        getParams.put(createParameter("Name", "string", "Şehir adı"));
        getParams.put(createParameter("CountryCode", "string", "Ülke kodu"));
        getParams.put(createParameter("District", "string", "Bölge"));
        getParams.put(createParameter("Population", "integer", "Nüfus"));
        get.put("parameters", getParams);
        get.put("responses", createResponses("City", true));
        paths.put("get", get);
        
        // POST /cities
        JSONObject post = new JSONObject();
        post.put("summary", "Yeni şehir ekle");
        post.put("operationId", "addCity");
        post.put("requestBody", createRequestBody("City", "Eklenecek şehir bilgileri"));
        post.put("responses", createPostResponses());
        paths.put("post", post);
        
        // PUT /cities
        JSONObject put = new JSONObject();
        put.put("summary", "Şehir güncelle");
        put.put("operationId", "updateCity");
        put.put("requestBody", createRequestBody("City", "Güncellenecek şehir bilgileri"));
        put.put("responses", createPutResponses());
        paths.put("put", put);
        
        // DELETE /cities
        JSONObject delete = new JSONObject();
        delete.put("summary", "Şehir sil");
        delete.put("operationId", "deleteCity");
        org.json.JSONArray deleteParams = new org.json.JSONArray();
        deleteParams.put(createParameter("ID", "integer", "Silinecek şehir ID'si"));
        delete.put("parameters", deleteParams);
        delete.put("responses", createDeleteResponses());
        paths.put("delete", delete);
        
        return paths;
    }
    
    private JSONObject createCountriesPaths() {
        JSONObject paths = new JSONObject();
        
        // GET /countries
        JSONObject get = new JSONObject();
        get.put("summary", "Ülkeleri listele");
        get.put("description", "Tüm ülkeleri listeler veya filtreleme parametreleri ile arama yapar");
        get.put("operationId", "getCountries");
        get.put("responses", createResponses("Country", true));
        paths.put("get", get);
        
        // POST /countries
        JSONObject post = new JSONObject();
        post.put("summary", "Yeni ülke ekle");
        post.put("operationId", "addCountry");
        post.put("requestBody", createRequestBody("Country", "Eklenecek ülke bilgileri"));
        post.put("responses", createPostResponses());
        paths.put("post", post);
        
        // PUT /countries
        JSONObject put = new JSONObject();
        put.put("summary", "Ülke güncelle");
        put.put("operationId", "updateCountry");
        put.put("requestBody", createRequestBody("Country", "Güncellenecek ülke bilgileri"));
        put.put("responses", createPutResponses());
        paths.put("put", put);
        
        // DELETE /countries
        JSONObject delete = new JSONObject();
        delete.put("summary", "Ülke sil");
        delete.put("operationId", "deleteCountry");
        org.json.JSONArray deleteParams = new org.json.JSONArray();
        deleteParams.put(createParameter("Code", "string", "Silinecek ülke kodu"));
        delete.put("parameters", deleteParams);
        delete.put("responses", createDeleteResponses());
        paths.put("delete", delete);
        
        return paths;
    }
    
    private JSONObject createCountryLanguagesPaths() {
        JSONObject paths = new JSONObject();
        
        // GET /countrylanguages
        JSONObject get = new JSONObject();
        get.put("summary", "Ülke dillerini listele");
        get.put("description", "Tüm ülke dillerini listeler veya filtreleme parametreleri ile arama yapar");
        get.put("operationId", "getCountryLanguages");
        get.put("responses", createResponses("CountryLanguage", true));
        paths.put("get", get);
        
        // POST /countrylanguages
        JSONObject post = new JSONObject();
        post.put("summary", "Yeni ülke dili ekle");
        post.put("operationId", "addCountryLanguage");
        post.put("requestBody", createRequestBody("CountryLanguage", "Eklenecek ülke dili bilgileri"));
        post.put("responses", createPostResponses());
        paths.put("post", post);
        
        // PUT /countrylanguages
        JSONObject put = new JSONObject();
        put.put("summary", "Ülke dili güncelle");
        put.put("operationId", "updateCountryLanguage");
        put.put("requestBody", createRequestBody("CountryLanguage", "Güncellenecek ülke dili bilgileri"));
        put.put("responses", createPutResponses());
        paths.put("put", put);
        
        // DELETE /countrylanguages
        JSONObject delete = new JSONObject();
        delete.put("summary", "Ülke dili sil");
        delete.put("operationId", "deleteCountryLanguage");
        org.json.JSONArray deleteParams = new org.json.JSONArray();
        deleteParams.put(createParameter("CountryCode", "string", "Ülke kodu"));
        deleteParams.put(createParameter("Language", "string", "Dil adı"));
        delete.put("parameters", deleteParams);
        delete.put("responses", createDeleteResponses());
        paths.put("delete", delete);
        
        return paths;
    }
    
    private JSONObject createParameter(String name, String type, String description) {
        JSONObject param = new JSONObject();
        param.put("name", name);
        param.put("in", "query");
        param.put("description", description);
        param.put("required", false);
        JSONObject schema = new JSONObject();
        schema.put("type", type);
        param.put("schema", schema);
        return param;
    }
    
    private JSONObject createRequestBody(String schemaName, String description) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("description", description);
        requestBody.put("required", true);
        JSONObject content = new JSONObject();
        JSONObject applicationJson = new JSONObject();
        JSONObject schema = new JSONObject();
        schema.put("$ref", "#/components/schemas/" + schemaName);
        applicationJson.put("schema", schema);
        content.put("application/json", applicationJson);
        requestBody.put("content", content);
        return requestBody;
    }
    
    private JSONObject createResponses(String schemaName, boolean isArray) {
        JSONObject responses = new JSONObject();
        JSONObject ok = new JSONObject();
        ok.put("description", "Başarılı yanıt");
        JSONObject content = new JSONObject();
        JSONObject applicationJson = new JSONObject();
        JSONObject schema = new JSONObject();
        if (isArray) {
            schema.put("type", "array");
            JSONObject items = new JSONObject();
            items.put("$ref", "#/components/schemas/" + schemaName);
            schema.put("items", items);
        } else {
            schema.put("$ref", "#/components/schemas/" + schemaName);
        }
        applicationJson.put("schema", schema);
        content.put("application/json", applicationJson);
        ok.put("content", content);
        responses.put("200", ok);
        responses.put("400", createErrorResponse("Geçersiz istek"));
        responses.put("500", createErrorResponse("Sunucu hatası"));
        return responses;
    }
    
    private JSONObject createPostResponses() {
        JSONObject responses = new JSONObject();
        JSONObject created = new JSONObject();
        created.put("description", "Başarıyla oluşturuldu");
        responses.put("201", created);
        responses.put("400", createErrorResponse("Geçersiz istek"));
        responses.put("500", createErrorResponse("Sunucu hatası"));
        return responses;
    }
    
    private JSONObject createPutResponses() {
        JSONObject responses = new JSONObject();
        JSONObject ok = new JSONObject();
        ok.put("description", "Başarıyla güncellendi");
        responses.put("200", ok);
        responses.put("404", createErrorResponse("Bulunamadı"));
        responses.put("400", createErrorResponse("Geçersiz istek"));
        return responses;
    }
    
    private JSONObject createDeleteResponses() {
        JSONObject responses = new JSONObject();
        JSONObject noContent = new JSONObject();
        noContent.put("description", "Başarıyla silindi");
        responses.put("204", noContent);
        responses.put("404", createErrorResponse("Bulunamadı"));
        responses.put("400", createErrorResponse("Geçersiz istek"));
        return responses;
    }
    
    private JSONObject createErrorResponse(String description) {
        JSONObject response = new JSONObject();
        response.put("description", description);
        JSONObject content = new JSONObject();
        JSONObject applicationJson = new JSONObject();
        JSONObject schema = new JSONObject();
        schema.put("$ref", "#/components/schemas/Error");
        applicationJson.put("schema", schema);
        content.put("application/json", applicationJson);
        response.put("content", content);
        return response;
    }
    
    private JSONObject createCitySchema() {
        JSONObject schema = new JSONObject();
        schema.put("type", "object");
        schema.put("required", new org.json.JSONArray().put("ID").put("Name").put("CountryCode").put("District").put("Population"));
        JSONObject properties = new JSONObject();
        properties.put("ID", createProperty("integer", "int32", "Şehir ID'si"));
        properties.put("Name", createProperty("string", null, "Şehir adı"));
        properties.put("CountryCode", createProperty("string", null, "Ülke kodu (3 karakter)"));
        properties.put("District", createProperty("string", null, "Bölge"));
        properties.put("Population", createProperty("integer", "int32", "Nüfus"));
        schema.put("properties", properties);
        return schema;
    }
    
    private JSONObject createCountrySchema() {
        JSONObject schema = new JSONObject();
        schema.put("type", "object");
        schema.put("required", new org.json.JSONArray().put("Code").put("Name").put("Continent").put("Region").put("SurfaceArea").put("Population"));
        JSONObject properties = new JSONObject();
        properties.put("Code", createProperty("string", null, "Ülke kodu (3 karakter)"));
        properties.put("Name", createProperty("string", null, "Ülke adı"));
        properties.put("Continent", createProperty("string", null, "Kıta"));
        properties.put("Region", createProperty("string", null, "Bölge"));
        properties.put("SurfaceArea", createProperty("number", "double", "Yüzölçümü"));
        properties.put("IndepYear", createProperty("integer", "int32", "Bağımsızlık yılı (opsiyonel)"));
        properties.put("Population", createProperty("integer", "int32", "Nüfus"));
        properties.put("LifeExpectancy", createProperty("number", "double", "Yaşam beklentisi (opsiyonel)"));
        properties.put("GNP", createProperty("number", "double", "GNP (opsiyonel)"));
        properties.put("GNPOld", createProperty("number", "double", "Eski GNP (opsiyonel)"));
        properties.put("LocalName", createProperty("string", null, "Yerel isim"));
        properties.put("GovernmentForm", createProperty("string", null, "Yönetim şekli"));
        properties.put("HeadOfState", createProperty("string", null, "Devlet başkanı"));
        properties.put("Capital", createProperty("integer", "int32", "Başkent ID'si (opsiyonel)"));
        properties.put("Code2", createProperty("string", null, "Ülke kodu 2 (2 karakter)"));
        schema.put("properties", properties);
        return schema;
    }
    
    private JSONObject createCountryLanguageSchema() {
        JSONObject schema = new JSONObject();
        schema.put("type", "object");
        schema.put("required", new org.json.JSONArray().put("CountryCode").put("Language").put("IsOfficial").put("Percentage"));
        JSONObject properties = new JSONObject();
        properties.put("CountryCode", createProperty("string", null, "Ülke kodu (3 karakter)"));
        properties.put("Language", createProperty("string", null, "Dil adı"));
        properties.put("IsOfficial", createProperty("string", null, "Resmi dil mi? (T/F)"));
        properties.put("Percentage", createProperty("number", "double", "Yüzde"));
        schema.put("properties", properties);
        return schema;
    }
    
    private JSONObject createErrorSchema() {
        JSONObject schema = new JSONObject();
        schema.put("type", "object");
        JSONObject properties = new JSONObject();
        properties.put("error", createProperty("string", null, "Hata mesajı"));
        schema.put("properties", properties);
        return schema;
    }
    
    private JSONObject createProperty(String type, String format, String description) {
        JSONObject prop = new JSONObject();
        prop.put("type", type);
        if (format != null) {
            prop.put("format", format);
        }
        prop.put("description", description);
        return prop;
    }
}

