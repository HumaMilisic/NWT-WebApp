package com.example;

import com.example.repo.DokumentRepository;
import com.example.utils.KeyGen;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;

@Controller
public class DigitalSignatureController {
    private DokumentRepository repo;

    private static final String filesRoot = System.getenv("OPENSHIFT_DATA_DIR")+"/dokumenti/";
    //private static final String filesRoot = "F:\\appDocs";

    @RequestMapping(method = RequestMethod.GET, value = "/publickey") //, produces = MediaType.APPLICATION_JSON_VALUE)
    public void providePublicKey(HttpServletResponse response) {
        //provjeri postoji li javni kljuc, ako ne postoji generisi privatni i javni
        //vrati javni kljuc u fajlu

        String fileName = "public.key";
        //File file = new File(filesRoot + "/" + fileName);

        InputStream myStream;
        try {
            byte[] encodedPublic = KeyGen.getKeyPair().getPublic().getEncoded();
            myStream = new ByteArrayInputStream(encodedPublic);

            // Set the content type and attachment header.
            response.addHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setContentType("txt/plain");
            response.setContentLength(encodedPublic.length);

            // Copy the stream to the response's output stream.
            try {
                IOUtils.copy(myStream, response.getOutputStream());
                response.flushBuffer();
            }
            catch (IOException ioe) {}
        }
        //catch (FileNotFoundException fnfe) {
        //debug
        catch(Throwable fnfe) {}
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signature/{fileName:.+}")
    public void getDigitalSignature(@PathVariable String fileName, HttpServletResponse response) { //HttpServletResponse response, HttpServletRequest request) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Path dir = Paths.get(filesRoot + "/" + SecurityContextHolder.getContext().getAuthentication().getName()+"/signatures/");
//            Files.createDirectories(dir);
            File signature = new File(dir.toString() +"/"+ fileName + ".sgn");
            //FileInputStream fis = new FileInputStream(new File(filesRoot + "/" + fileName + ".sgn"));
            FileInputStream fis = new FileInputStream(signature);

            // Set the content type and attachment header.
            response.addHeader("Content-disposition", "attachment;filename=" + fileName + ".sgn");
            response.setContentType("txt/plain");
            response.setContentLength((int)signature.length());

            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        }
        catch(FileNotFoundException fnfe) {}
        catch (IOException ioe) {}
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/sign/{fileName:.+}")
    public @ResponseBody
    ResponseEntity<String> signDocument(
                @PathVariable String fileName) {
        try {
            //String name = file.getOriginalFilename();
            //provjeri postoji li javni kljuc, ako ne postoji generisi privatni i javni
            //nadji fajl po imenu (ako postoji) i potpisi ga

            KeyPair pair = KeyGen.getKeyPair();

            PrivateKey priv = pair.getPrivate();
            //PublicKey pub = pair.getPublic();

            //potpisivanje podataka
            Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");

            dsa.initSign(priv);

            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            Path dir = Paths.get(filesRoot + "/" + SecurityContextHolder.getContext().getAuthentication().getName());
//            Files.createDirectories(dir);
//            File signature = new File(dir.toString() + fileName + ".sgn");

            //dsa.initSign(priv);
            File file = new File(dir.toString() + "/" + fileName);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bufin = new BufferedInputStream(fis);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufin.read(buffer)) >= 0) {
                dsa.update(buffer, 0, len);
            }
            bufin.close();

            byte[] realSig = dsa.sign();

                /* save the signature in a file */
            dir = Paths.get(filesRoot + "/" +
                    SecurityContextHolder.getContext().getAuthentication().getName()+ "/signatures/");
            Files.createDirectories(dir);
            FileOutputStream sigfos = new FileOutputStream(new File(dir.toString()+'/'+ fileName + ".sgn"));
            sigfos.write(realSig);
            sigfos.close();

            //ovo sad vise nije neophodno jer se kljucevi generisu samo jednom
            //bit ce izbaceno osim ako se ne odluci da svaki korisnik ima vlastiti par kljuceva
            //trenutno je jos potrebno, jer se svakim restartom generisu novi kljucevi, tako da se ne bi mogli provjeriti stari potpisi
            //osim ako jednom generisan kljuc ne bude sacuvan u fajl, i onda uvijek preuzet iz njega
                /* save the public key in a file */
            /*byte[] key = pub.getEncoded();
            FileOutputStream keyfos = new FileOutputStream(new File(filesRoot + "\\" + username + "\\signatures\\" + fileName + ".key"));
            keyfos.write(key);
            keyfos.close();*/

            return new ResponseEntity<String>("{ \"status\": \"Fajl uspjesno potpisan!\"," +
                    "\"public-key\": \"Fajl uspjesno potpisan!\" }",
                    HttpStatus.ACCEPTED);
        }
        catch (Throwable throwable) {
            return new ResponseEntity<String>(throwable.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletesignature/{fileName:.+}")
    public @ResponseBody String delete(@PathVariable String fileName) {
        return "{ \"status\": \"blahblah\" }";
    }
}
