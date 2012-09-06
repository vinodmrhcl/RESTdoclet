package com.iggroup.oss.restdoclet.plugin.io;

import java.io.File;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * Utility class to upload a jar to the RESTdoclet web app where it will be
 * extracted to a specified deployment directory
 */
public class FileUploader {

   /**
    * The POST header key specifying the target deployment folder on the web
    * server
    */
   private static final String RESTDOCLET_DEPLOY = "RESTDOCLET_DEPLOY";

   /**
    * The logger
    */
   private static final Logger LOG = Logger.getLogger(FileUploader.class);

   /**
    * Method to upload (post) a jar to the RESTDoclet web app at the given url,
    * and store it on the server in the given location.
    * 
    * @param url the RESTDoclet web app url to which to post
    * @param deployDir the RESTDoclet web app directory where the jar will be
    *           extracted
    * @param file the jar to be uploaded
    */
   public static void upload(String url, String deployDir, File file) {

      LOG.debug("Uploading " + file.getName() + " to " + url);
      try {
         HttpClient httpclient = new DefaultHttpClient();
         HttpPost httppost = new HttpPost(url);

         MultipartEntity reqEntity =
            new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

         FileBody bin = new FileBody(file);
         reqEntity.addPart("attachment_field", bin);

         httppost.setEntity(reqEntity);
         httppost.setHeader(RESTDOCLET_DEPLOY, deployDir);

         HttpResponse response = httpclient.execute(httppost);

         if (response.getStatusLine().getStatusCode() != 200) {
            LOG.error("Failed to upload " + file.getName() + " to " + url
               + " : " + response.getStatusLine());
         }

      } catch (Exception e) {
         LOG.error("Failed to upload " + file.getName() + " to " + url, e);
      }

   }
}