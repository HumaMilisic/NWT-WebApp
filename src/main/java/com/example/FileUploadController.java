package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.example.models.Dokument;
import com.example.repo.DokumentRepository;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.asprise.ocr.Ocr;

//ako ovo zakomentarišem ne može ga naći
@Controller
public class FileUploadController { //extends HttpServlet {
    private DokumentRepository repo;

    private static final String filesRoot = System.getenv("OPENSHIFT_DATA_DIR");
    //private static final String filesRoot = "F:\\appDocs";

    @RequestMapping(method = RequestMethod.GET, value = "/document") //, produces = MediaType.APPLICATION_JSON_VALUE)
    //public String provideUploadInfo(Model model) {
    //public Iterable<Dokument> provideUploadInfo() {
    public void provideUploadInfo(HttpServletResponse response) {

        //File rootFolder = new File(Application.ROOT);
        /*File rootFolder = new File(filesRoot);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName())
                        .collect(Collectors.toList())
        );

        return "uploadForm";*/

        // Ovdje NullPointerException ?
        //List<Dokument> docs = (List<Dokument>)repo.findAll();

        //ovako bi radilo kad bi fajlovi fakat isli u korisnicki folder
        //File rootFolder = new File(filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName());
        //List<String> files = new ArrayList<String>();
        //for (File file : rootFolder.listFiles())
        //    files.add(file.getName());

        //ali posto on kreira fajlove sa losim imenom, onda losi dio imena mozemo posmatrati kao prefiks
        File rootFolder = new File(filesRoot);
        List<String> files = new ArrayList<String>();
        for (File file : rootFolder.listFiles()) {
            if(file.getName().startsWith("\\" + SecurityContextHolder.getContext().getAuthentication().getName() + "\\")) {
                files.add(file.getName().split("\\\\")[2]);
                //alternativno
                files.add(file.getName().substring(SecurityContextHolder.getContext().getAuthentication().getName().length() + 2));
            }
        }

        //Iterable<Dokument> docs = repo.findAll();
        //return docs;

        //return files;
        //return new ResponseEntity<String>(files.toString(), HttpStatus.OK);
        //test
        String ret;
        if(files.isEmpty()) {
            ret = "{ \"docs\" : [] }";
        }
        else {
            ret = "{ \"docs\" : [ ";
            for (String s : files)
                ret += ("\"" + s + "\", ");
            ret = ret.substring(0, ret.length() - 2);
            ret += " ] }";
        }
        try {
            //response.getWriter().write("{ \"docs\" :" + files.toString() + " }");
            response.getWriter().write(ret);
        }
        catch (IOException ioe) {

        }
    }

    //@RequestMapping(value="download", method=RequestMethod.GET)
    @RequestMapping(method = RequestMethod.GET, value = "/document/{fileName:.+}")
    public void getDownload(@PathVariable String fileName, HttpServletResponse response) { //HttpServletResponse response, HttpServletRequest request) {

        // Get your file stream from wherever.
        File rootFolder = new File(filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName());

        //debug
        /*try {
            PrintWriter writer = response.getWriter();
            writer.write(request.getPathInfo());
            writer.write(request.getPathTranslated());
            writer.write(request.getRequestURI());
            writer.write(request.getRequestURL().toString());
        }
        catch (Throwable ioetest) {

        }*/
        //


        File file = new File(rootFolder + "\\" + fileName); //debug// + "\\" + request.getPathInfo().substring(1));

        InputStream myStream;
        try {
            myStream = new FileInputStream(file);

            // Set the content type and attachment header.
            //response.addHeader("Content-disposition", "attachment;filename=myfilename.txt");
            response.addHeader("Content-disposition", "attachment;filename=" + fileName);
            //response.addHeader("Content-disposition", "attachment;filename=" + file.length());
            //response.addHeader("test", "test"); // ne radi ovo :'(
            response.setContentType("txt/plain");
            //ServletContext context = getServletConfig().getServletContext();
            //String mimetype = context.getMimeType(rootFolder + "\\" + SecurityContextHolder.getContext().getAuthentication().getName() + "\\" + request.getPathInfo().substring(1));// + filePart.getSubmittedFileName());
            //response.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
            response.setContentLength( (int)file.length() );
            //response.setHeader( "Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" );


            // Copy the stream to the response's output stream.
            try {
                IOUtils.copy(myStream, response.getOutputStream());
                response.flushBuffer();
                //response.getWriter().write(file.getName() + " - " + file.length());
            }
            catch (IOException ioe) {
                /*try {
                    response.getWriter().write(request.getPathInfo().substring(1));
                }
                catch (IOException ioe2) {

                }*/
            }
        }
        //catch (FileNotFoundException fnfe) {
        //debug
        catch(Throwable fnfe) {
        //
            //try {
            //    response.getWriter().write(request.getPathInfo().substring(1) + fnfe.getStackTrace()[0].toString());
            //}
            //catch (IOException ioe2) {

            //}
        }
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/document/{fileName}")
//    public @ResponseBody File Download(@PathVariable String fileName) {
        //File rootFolder = new File(Application.ROOT);
//        File rootFolder = new File(filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName());
        //List<String> fileNames = Arrays.stream(rootFolder.listFiles())
        //        .map(f -> f.getName())
        //        .collect(Collectors.toList());

        //model.addAttribute("files",
        //        Arrays.stream(rootFolder.listFiles())
        //                .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
        //                .map(f -> f.getName())
        //                .collect(Collectors.toList())
        //);
//        File file = new File(rootFolder + "\\" + fileName);
        //return "uploadForm";
//        return file;
//    }

    //@RequestMapping(method = RequestMethod.POST, value = "/upload")
    //public String handleFileUpload(@RequestParam("name") String name,
    //                                             @RequestParam("file") MultipartFile file,
    //                                             RedirectAttributes redirectAttributes) {
    @CrossOrigin //(origins = "http://localhost:8181")
    @RequestMapping(method = RequestMethod.POST, value = "/document")
    //public @ResponseBody String handleFileUpload(@RequestBody @RequestParam("name") String name,
    //                               @RequestBody @RequestParam("file") MultipartFile file,
    //                               RedirectAttributes redirectAttributes) {
    public @ResponseBody String handleFileUpload(//@RequestBody String name,
                @RequestBody MultipartFile file) { //,
        String name = file.getOriginalFilename(); //getName();
                //RedirectAttributes redirectAttributes) {
        if (name.contains("/")) {
            //redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
            //return "redirect:upload";
            return "{ \"status\": \"Ime ne smije sadrzavati znak '/'!\" }";
        }
        if (name.contains("/")) {
            //redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            //return "redirect:upload";
            return "{ \"status\": \"Ime ne smije sadrzavati znak '/'!\" }";
        }

        if (!file.isEmpty()) {
            try {
                File usersFolder = new File(filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName());
                if(!usersFolder.exists()) usersFolder.mkdirs();

                File f = new File(usersFolder + "\\" + name);

                Ocr ocr = new Ocr();
                ocr.startEngine("eng", Ocr.SPEED_FASTEST);

                BufferedOutputStream stream = new BufferedOutputStream(
                        //new FileOutputStream(new File(Application.ROOT + "/" + name)));
                        //new FileOutputStream(new File(filesRoot + "/" + name)));
                        new FileOutputStream(new File(filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName() + "\\" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);


                stream.close();

                ocr.recognize(new File[] {f},
                        Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PDF, Ocr.PROP_PDF_OUTPUT_FILE, filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName() + "\\" + name, Ocr.PROP_PDF_OUTPUT_TEXT_VISIBLE, false);
                ocr.stopEngine();
                //redirectAttributes.addFlashAttribute("message",
                //        "You successfully uploaded " + name + "!");
            }
            catch (Exception e) {
                e.printStackTrace();
                //return e.getMessage(); // OVO JE SAMO ZA DEBUGGING !

                //redirectAttributes.addFlashAttribute("message",
                //        "You failed to upload " + name + " => " + e.getMessage());
            }
        }
        else {
            //redirectAttributes.addFlashAttribute("message",
            //        "You failed to upload " + name + " because the file was empty");
        }

        //return "redirect:upload";
        return "{ \"status\": \"Fajl uspjesno uploadovan!\" }";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/document/{fileName:.+}")
    public @ResponseBody String delete(@PathVariable String fileName) {
        File rootFolder = new File(filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName());
        File file = new File(rootFolder + "\\" + fileName);
        if(file.delete())
            return "{ \"status\": \"Fajl je uspjesno obrisan!\" }";
        else
            return "{ \"status\": \"Fajl nije obrisan!\" }";
    }
}
