package com.example;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.repo.DokumentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {
    private DokumentRepository repo;

    private static final String filesRoot = System.getenv("OPENSHIFT_DATA_DIR");
    //private static final String filesRoot = "F:\\appDocs";

    @RequestMapping(method = RequestMethod.GET, value = "/document")
    public String provideUploadInfo(Model model) {
        //File rootFolder = new File(Application.ROOT);
        File rootFolder = new File(filesRoot);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName())
                        .collect(Collectors.toList())
        );

        return "uploadForm";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/document/{fileName}")
    public File Download(@PathVariable String fileName) {
        //File rootFolder = new File(Application.ROOT);
        File rootFolder = new File(filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName());
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        //model.addAttribute("files",
        //        Arrays.stream(rootFolder.listFiles())
        //                .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
        //                .map(f -> f.getName())
        //                .collect(Collectors.toList())
        //);
        File file = new File(rootFolder + "\\" + fileName);
        //return "uploadForm";
        return file;
    }

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
            return "redirect:upload";
        }
        if (name.contains("/")) {
            //redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            return "redirect:upload";
        }

        if (!file.isEmpty()) {
            try {
                File usersFolder = new File(filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName());
                if(!usersFolder.exists()) usersFolder.mkdirs();
                BufferedOutputStream stream = new BufferedOutputStream(
                        //new FileOutputStream(new File(Application.ROOT + "/" + name)));
                        //new FileOutputStream(new File(filesRoot + "/" + name)));
                        new FileOutputStream(new File(filesRoot + "\\" + SecurityContextHolder.getContext().getAuthentication().getName() + "\\" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
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

        return "redirect:upload";
    }

}
