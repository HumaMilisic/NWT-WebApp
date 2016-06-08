package com.example.utils;

//import org.apache.tomcat.util.http.fileupload.IOUtils;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Owner on 10.12.2015.
 */
public class KeyGen {
    private static final String filesRoot = System.getenv("OPENSHIFT_DATA_DIR")+"/dokumenti/";
    private static KeyPair keypair = null;

    public static KeyPair getKeyPair() {
        if(keypair == null) {
            //probaj procitati iz fajla
            try {
                //FileInputStream priv = new FileInputStream(new File(filesRoot + "/" + "private.key").);
                //byte[] privkey = IOUtils.toByteArray(priv);
                //Path path = Paths.get(filesRoot + "/private.key");
                //byte[] privkey = Files.readAllBytes(path);
                FileInputStream keyfis = new FileInputStream(new File(filesRoot + "/public.key"));
                byte[] pubkey = new byte[keyfis.available()];
                keyfis.read(pubkey);

                FileInputStream keyfis2 = new FileInputStream(new File(filesRoot + "/private.key"));
                byte[] privkey = new byte[keyfis2.available()];
                keyfis2.read(privkey);

                keyfis.close();
                keyfis2.close();

                X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubkey);
                KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
                PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);

                PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privkey);
                PrivateKey privateKey = keyFactory.generatePrivate(privKeySpec);

                keypair = new KeyPair(publicKey, privateKey);
            }
            catch (InvalidKeySpecException ikse) { }
            catch (NoSuchProviderException nspo) { }
            catch (NoSuchAlgorithmException nsao) { }
            catch (FileNotFoundException fnfe) { }
            catch (IOException ioe) { }
        }
        if(keypair == null) {
            //probaj generisati novi
            try {
                //generisanje javnog i privatnog kljuca
                KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");

                SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
                keyGen.initialize(1024, random);

                keypair = keyGen.generateKeyPair();

                try {
                    byte[] privkey = keypair.getPrivate().getEncoded();
                    FileOutputStream priv = new FileOutputStream(new File(filesRoot + "/private.key"));
                    priv.write(privkey);
                    priv.close();

                    byte[] pubkey = keypair.getPublic().getEncoded();
                    FileOutputStream pub = new FileOutputStream(new File(filesRoot + "/public.key"));
                    pub.write(pubkey);
                    pub.close();
                }
                catch (FileNotFoundException fnfe) {

                }
                catch (IOException ioe) {

                }
            }
            catch (NoSuchAlgorithmException nsae) {
                keypair = null;
            }
            catch (NoSuchProviderException nspe) {
                keypair = null;
            }
        }
        return keypair;
    }
}
