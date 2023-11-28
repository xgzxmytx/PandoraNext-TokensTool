package com.yyandywt99.pandoraNext.service.impl;

import com.yyandywt99.pandoraNext.pojo.systemSetting;
import com.yyandywt99.pandoraNext.pojo.validation;
import com.yyandywt99.pandoraNext.service.systemService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yangyang
 * @create 2023-11-18 9:54
 */
@Slf4j
@Service
public class systemServiceImpl implements systemService {
    @Value("${deployPosition}")
    private String deployPosition;
    private String deploy = "default";

    public String selectFile(){
        String projectRoot;
        if(deploy.equals(deployPosition)){
            projectRoot = System.getProperty("user.dir");
        }
        else{
            projectRoot = deployPosition;
        }
        String parent = projectRoot + File.separator + "config.json";
        File jsonFile = new File(parent);
        Path jsonFilePath = Paths.get(parent);
        // 如果 JSON 文件不存在，创建一个新的 JSON 对象
        if (!jsonFile.exists()) {
            try {
                // 创建文件config.json
                Files.createFile(jsonFilePath);
                // 往 config.json 文件中添加一个空数组，防止重启报错
                Files.writeString(jsonFilePath, "{}");
                System.out.println("空数组添加完成");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("config.json创建完成: " + jsonFilePath);
        }
        return parent;
    }
    /**
     * 修改config.json里的系统值
     * @return "修改成功！"or"修改失败"
     */
    @Override
    public String requiredSetting(systemSetting tem){
        String parent = selectFile();
        try {
            // 读取 JSON 文件内容
            String jsonContent = new String(Files.readAllBytes(Paths.get(parent)));

            JSONObject jsonObject = new JSONObject(jsonContent);
            updateJsonValue(jsonObject,"bind",tem.getBing());
            updateJsonValue(jsonObject,"timeout",tem.getTimeout());
            updateJsonValue(jsonObject,"proxy_url",tem.getProxy_url());
            updateJsonValue(jsonObject,"public_share",tem.getPublic_share());
            updateJsonValue(jsonObject,"site_password",tem.getSite_password());
            updateJsonValue(jsonObject,"setup_password",tem.getSetup_password());
            JSONArray jsonArray = null;
            if (tem.getWhitelist() != null && tem.getWhitelist().length() > 0 && tem.getWhitelist() != "null") {
                String numbersString = tem.getWhitelist().replaceAll("[\\[\\]]", "");
                String[] numbersArray = numbersString.split(",");
                // 将数组转换为 List<String>
                List<String> numbersList = new ArrayList<>(Arrays.asList(numbersArray));
                jsonArray = new JSONArray(numbersList);
                jsonObject.put("whitelist",jsonArray);
            }
            else {
                jsonObject.put("whitelist", JSONObject.NULL);
            }
            updateJsonValue(jsonObject,"license_id",tem.getLicense_id());
            updateJsonValue(jsonObject,"loginUsername",tem.getLoginUsername());
            updateJsonValue(jsonObject,"loginPassword",tem.getLoginPassword());
            updateJsonValue(jsonObject,"server_mode",tem.getServer_mode());
            updateJsonValue(jsonObject,"autoToken_url",tem.getAutoToken_url());
            updateJsonValue(jsonObject,"tokenKind",tem.getTokenKind());
            
            validation validation = tem.getValidation();
            JSONObject captchaJson = jsonObject.getJSONObject("captcha");
            updateJsonValue(captchaJson, "provider", validation.getProvider());
            updateJsonValue(captchaJson, "site_key", validation.getSite_key());
            updateJsonValue(captchaJson, "site_secret", validation.getSite_secret());
            updateJsonValue(captchaJson, "site_login", validation.isSite_login());
            updateJsonValue(captchaJson, "setup_login", validation.isSetup_login());
            updateJsonValue(captchaJson, "oai_username", validation.isOai_username());
            updateJsonValue(captchaJson, "oai_password", validation.isOai_password());
            // 将修改后的 JSONObject 转换为格式化的 JSON 字符串
            String updatedJson = jsonObject.toString(2);
            Files.write(Paths.get(parent), updatedJson.getBytes());
            return "修改config.json成功，快去重启PandoraNext吧!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "修改config.json失败";
    }

    private void updateJsonValue(JSONObject jsonObject, String key, Object value) {
        try {
            if (value != null && value.toString().length() > 0) {
                jsonObject.put(key, value);
            } else {
                jsonObject.put(key, "");
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 查询config.json里的系统值
     * @return systemSettings类
     */
    public systemSetting selectSetting(){
        String parent = selectFile();
        try {
            // 读取 JSON 文件内容
            String jsonContent = new String(Files.readAllBytes(Paths.get(parent)));
            // 将 JSON 字符串解析为 JSONObject
            JSONObject jsonObject = new JSONObject(jsonContent);

            // 将 JSONObject 转换为 Config 类的实例
            systemSetting config = new systemSetting();
            config.setSite_password(jsonObject.optString("site_password"));
            config.setSetup_password(jsonObject.optString("setup_password"));
            config.setBing(jsonObject.optString("bind"));
            config.setPublic_share(jsonObject.optBoolean("public_share"));
            config.setProxy_url(jsonObject.optString("proxy_url"));
            config.setWhitelist(jsonObject.isNull("whitelist") ? null : jsonObject.optString("whitelist"));
            config.setTimeout(jsonObject.getInt("timeout"));
            try {
                jsonObject.getString("loginUsername");
            } catch (JSONException e) {
                jsonObject.put("loginUsername", "root");
                log.info("config.json没有新增loginUsername参数,现已增加！");
            }
            try {
                jsonObject.getString("loginPassword");
            } catch (JSONException e) {
                jsonObject.put("loginPassword", "123456");
                log.info("config.json没有新增loginPassword参数,现已增加！");
            }
            try {
                jsonObject.getString("license_id");
            } catch (JSONException e) {
                jsonObject.put("license_id", "");
                log.info("config.json没有新增license_id参数,现已增加！");
            }
            try {
                jsonObject.getString("server_mode");
            } catch (JSONException e) {
                jsonObject.put("server_mode", "web");
                log.info("config.json没有新增server_mode参数,现已增加！");
            }
            try {
                jsonObject.getString("autoToken_url");
            } catch (JSONException e) {
                jsonObject.put("autoToken_url", "default");
                log.info("config.json没有新增autoToken_url参数,现已增加！");
            }
            try {
                jsonObject.getString("tokenKind");
            } catch (JSONException e) {
                jsonObject.put("tokenKind", "access_token");
                log.info("config.json没有新增tokenKind参数,现已增加！");
            }
            config.setServer_mode(jsonObject.getString("server_mode"));
            config.setLoginUsername(jsonObject.getString("loginUsername"));
            config.setLoginPassword(jsonObject.getString("loginPassword"));
            config.setLicense_id(jsonObject.getString("license_id"));
            config.setAutoToken_url(jsonObject.getString("autoToken_url"));
            config.setTokenKind(jsonObject.getString("tokenKind"));
            // 将修改后的 JSONObject 转换为格式化的 JSON 字符串
            // 获取 captcha 相关属性
            JSONObject captchaJson = null;
            try {
                captchaJson = jsonObject.getJSONObject("captcha");
                validation captchaSetting = new validation();
                if (captchaJson != null) {
                    captchaSetting.setProvider(captchaJson.optString("provider"));
                    captchaSetting.setSite_key(captchaJson.optString("site_key"));
                    captchaSetting.setSite_secret(captchaJson.optString("site_secret"));
                    captchaSetting.setSite_login(captchaJson.optBoolean("site_login"));
                    captchaSetting.setSetup_login(captchaJson.optBoolean("setup_login"));
                    captchaSetting.setOai_username(captchaJson.optBoolean("oai_username"));
                    captchaSetting.setOai_password(captchaJson.optBoolean("oai_password"));
                    config.setValidation(captchaSetting);
                }
                else {
                    // 如果 captchaJson 不存在，设置默认值
                    validation defaultCaptchaSetting = new validation();
                    defaultCaptchaSetting.setProvider("");
                    defaultCaptchaSetting.setSite_key("");
                    defaultCaptchaSetting.setSite_secret("");
                    defaultCaptchaSetting.setSite_login(false);
                    defaultCaptchaSetting.setSetup_login(false);
                    defaultCaptchaSetting.setOai_username(false);
                    defaultCaptchaSetting.setOai_password(false);
                    JSONObject newJson = new JSONObject();
                    newJson.put("provider", defaultCaptchaSetting.getProvider());
                    newJson.put("site_key", defaultCaptchaSetting.getSite_key());
                    newJson.put("site_secret", defaultCaptchaSetting.getSite_secret());
                    newJson.put("site_login", defaultCaptchaSetting.isSite_login());
                    newJson.put("setup_login", defaultCaptchaSetting.isSetup_login());
                    newJson.put("oai_username", defaultCaptchaSetting.isOai_username());
                    newJson.put("oai_password", defaultCaptchaSetting.isOai_password());
                    jsonObject.put("captcha", newJson);
                    config.setValidation(defaultCaptchaSetting);
                }
            } catch (JSONException e) {
                // 获取captchaJson，如果不存在则创建一个默认的JSONObject
                captchaJson = jsonObject.optJSONObject("captcha");
                if (captchaJson == null) {
                    captchaJson = new JSONObject();
                }
                // 如果某个属性不存在，设置默认值
                captchaJson.put("provider", captchaJson.optString("provider", ""));
                captchaJson.put("site_key", captchaJson.optString("site_key", ""));
                captchaJson.put("site_secret", captchaJson.optString("site_secret", ""));
                captchaJson.put("site_login", captchaJson.optBoolean("site_login", false));
                captchaJson.put("setup_login", captchaJson.optBoolean("setup_login", false));
                captchaJson.put("oai_username", captchaJson.optBoolean("oai_username", false));
                captchaJson.put("oai_password", captchaJson.optBoolean("oai_password", false));
                jsonObject.put("captcha", captchaJson);
            }
            String updatedJson = jsonObject.toString(2);
            Files.write(Paths.get(parent), updatedJson.getBytes());
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
