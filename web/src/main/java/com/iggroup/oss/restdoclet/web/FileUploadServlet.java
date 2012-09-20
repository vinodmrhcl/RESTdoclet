package com.iggroup.oss.restdoclet.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * File upload servlet which accepts RESTDoclet generated config jars via POST
 * and extracts them to a location specified in the POST header.
 */
public class FileUploadServlet extends HttpServlet {

   /**
    * The POST header key specifying the target deployment folder on the web
    * server
    */
   private static final String RESTDOCLET_DEPLOY = "RESTDOCLET_DEPLOY";

   /**
    * The logger
    */
   private static final Logger LOG = Logger.getLogger(FileUploadServlet.class);

   private static String configPath;

   {
      configPath = System.getenv("RESTDOCLET_DEPLOY");
   }

   /* (non-Javadoc)
    * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    */
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      PrintWriter out = response.getWriter();
      out.print("Not supported");
   }

   /* (non-Javadoc)
    * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    * 
    * This method accepts a jar and extracts to a location specified by the header key RESTDOCLET_DEPLOY
    */
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      String deploySubDir = request.getHeader(RESTDOCLET_DEPLOY);
      String deployDir = configPath + File.separator + deploySubDir;

      LOG.info("Upload request to " + deployDir);
      if (ServletFileUpload.isMultipartContent(request))
      {
         try {

            File dir = new File(configPath);
            deleteDir(dir);

            List<FileItem> fileItems =
               new ServletFileUpload(new DiskFileItemFactory(1024 * 1024, dir))
            .parseRequest(request);

            for (FileItem item : fileItems)

            {
               if (item != null) {
                  LOG.debug(item.getName());
                  JarInputStream jis =
                     new JarInputStream(item.getInputStream());

                  JarEntry jarEntry;
                  while ((jarEntry = jis.getNextJarEntry()) != null) {
                     extract(jis, jarEntry, deployDir);
                  }
               }

            }

         } catch (Exception e) {
            LOG.error("Failed to upload to " + configPath, e);
         }

      }
   }

   /**
    * Delete the specified directory
    * 
    * @param dir directory to delete
    * @return true if the operation succeeded, otherwise return false
    */
   private static boolean deleteDir(File dir) {

      LOG.debug("Deleting " + dir);
      if (dir.isDirectory()) {
         String[] children = dir.list();
         for (int i = 0; i < children.length; i++) {
            boolean success = deleteDir(new File(dir, children[i]));
            if (!success) {
               return false;
            }
         }
      }

      // The directory is now empty so delete it
      return dir.delete();
   }

   /**
    * Extract a jar entry from a a jar
    * 
    * @param jin jar input stream
    * @param e the jar entry to extract
    * @param dir the location to which to extract the jar entry
    * @throws IOException something unexpected happened
    */
   private static void extract(JarInputStream jin, JarEntry e, String dir)
      throws IOException {

      LOG.debug("Extracting " + e.getName() + " to " + dir);

      File f =
         new File(dir + File.separatorChar
            + e.getName().replace('/', File.separatorChar));

      if (e.isDirectory()) {
         if (!f.exists() && !f.mkdirs() || !f.isDirectory()) {
            throw new IOException(f + ": could not create directory");
         }
      } else {
         if (f.getParent() != null) {
            File d = new File(f.getParent());
            if (!d.exists() && !d.mkdirs() || !d.isDirectory()) {
               throw new IOException(d + ": could not create directory");
            }
         }
         OutputStream os = new FileOutputStream(f);
         byte[] b = new byte[512];
         int len;
         while ((len = jin.read(b, 0, b.length)) != -1) {
            os.write(b, 0, len);
         }
         jin.closeEntry();
         os.close();
      }
   }

}
