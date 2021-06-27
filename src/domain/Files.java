package domain;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import domain.BST.BST;
import domain.BST.TreeException;
import domain.linkedList.ListException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author PaoVega
 */
public class Files {

   public boolean existFile(String address) {
        File file = new File(address);
        return file.exists();
    }

    public void addFood(Food food) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("foodFile.txt", true));//no sobreescibe
        try {
            bw.write(food.getName() + "," + food.getPrice() + "," + food.getRestaurantID());
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyFood(BST bst) throws IOException, TreeException {

        String result = "";

        String treeData[] = new String[bst.size()];
        treeData = bst.preOrderAux().split("\n");

        for (int i = 0; i < treeData.length; i++) {
            result += treeData[i];
        }

//        for (int i = 1; i <= list.size(); i++) {
//            Course course = (Course) list.getNode(i).data;
//            result += course.getId() + "," + course.getName() + ","
//                    + course.getCredits() + "," + course.getCarrerID() + "\n";//No trabajar con el id sino con e i
//        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("foodFile.txt"));//sobreescibe
        //        try {
        //            String course[] = result.split("\n");
        //            for (int i = 0; i < course.length; i++) {
        //                bw.write(course[i]);
        //                bw.newLine();
        //                bw.flush();
        //            }
        //            bw.close();
        //
        //        } catch (FileNotFoundException e) {
        //            System.out.println(e.getMessage());
        //        } catch (IOException e) {
        //            System.out.println(e.getMessage());
        //        }

//    public BST loadTreeFood(String fileDir, BST bst) throws FileNotFoundException, IOException{
//
//        BufferedReader br = new BufferedReader(new FileReader(fileDir));
//        String linea = br.readLine();
//
//        if (linea != null) {
//            while (linea != null) {
//                String food[] = linea.split(",");//Divide la linea en un array de String
//                bst.add(new Food(food[1], Double.parseDouble(food[2]), Integer.parseInt(food[4])));
//                linea = br.readLine();
//            }
//        }//Fin linea+
//        br.close();
//        return bst;
//    }
    }

    public BST loadFoodsBSTrees(String fileDir, BST tree) throws IOException {
        if (!existFile(fileDir)) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("foodFile.txt", true));
        }

        BufferedReader br = new BufferedReader(new FileReader(fileDir));
        String linea = br.readLine();

        if (linea != null) {
            while (linea != null) {
                Object foodS[] = linea.split(",");//Divide la linea en un array de String
                Food food = new Food(foodS[0].toString(), Double.parseDouble(foodS[1].toString()), Integer.parseInt(foodS[2].toString())); //Carga las comidas del txt
                tree.add(food);
                linea = br.readLine();
            }
        }//Fin linea+
        br.close();
        return tree;
    }
     public BST loadProductsBSTrees(String fileDir, BST tree) throws IOException {
        if (!existFile(fileDir)) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("products.txt", true));
        }

        BufferedReader br = new BufferedReader(new FileReader(fileDir));
        String linea = br.readLine();

        if (linea != null) {
            while (linea != null) {
                Object productS[] = linea.split(",");//Divide la linea en un array de String
                //Carga las comidas del txt
                Product product = new Product(productS[0].toString(), Double.parseDouble(productS[1].toString()), Integer.parseInt(productS[2].toString())); 
                tree.add(product);
                linea = br.readLine();
            }
        }//Fin linea+
        br.close();
        return tree;
     }


    public void addManager(String username, String password, String keyWord) throws IOException, ListException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("managersFile.txt", true));//no sobreescibe
        try {
            bw.write(username + "," + password + "," + keyWord);
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean readManagers(String fileDir, String username, String password) throws IOException {//Lee todos los managers ingresados

        BufferedReader br = new BufferedReader(new FileReader(fileDir));

        String linea = br.readLine();
        if (linea != null) {
            while (linea != null) {
                try {
                    String managers[] = linea.split(",");//Divide la linea en un array de Strings

                    String decyptedKey = dencrypt(managers[2], "DR54");

                    String dencryptedPassword = dencrypt(managers[1], decyptedKey);

                    if (managers[0].equalsIgnoreCase(username) && password.equalsIgnoreCase(dencryptedPassword)) {//Si encuentra un manager resgistrado
                        return true;
                    }
                    linea = br.readLine();
                } //Lee toda una linea
                catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }//Fin linea+
        br.close();
        return false;
    }

    public void cleanFile(String fileName) throws IOException {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        try {
            bw.write("");//Escribe un vacio en el archivo
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea la clave de encriptacion usada internamente
     *
     * @param clave Clave que se usara para encriptar
     * @return Clave de encriptacion
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private SecretKeySpec createKey(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] keyEncrypt = key.getBytes("UTF-8");

        MessageDigest sha = MessageDigest.getInstance("SHA-1");

        keyEncrypt = sha.digest(keyEncrypt);
        keyEncrypt = Arrays.copyOf(keyEncrypt, 16);

        //Construye una clave secreta apartir de una matriz de bytes dada
        SecretKeySpec secretKey = new SecretKeySpec(keyEncrypt, "AES");

        return secretKey;
    }

    /**
     * Aplica la encriptacion AES a la cadena de texto usando la clave indicada
     *
     * @param data Cadena a encriptar
     * @param secretK Clave para encriptar
     * @return Información encriptada
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String encrypt(String data, String secretK) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = this.createKey(secretK);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] dataEncrypt = data.getBytes("UTF-8");
        byte[] bytesEncrypt = cipher.doFinal(dataEncrypt);
        String encrypt = Base64.getEncoder().encodeToString(bytesEncrypt);

        return encrypt;
    }

    /**
     * Desencripta la cadena de texto indicada usando la clave de encriptacion
     *
     * @param dataEncrypt Datos encriptados
     * @param secretK Clave de encriptacion
     * @return Informacion desencriptada
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String dencrypt(String dataEncrypt, String secretK) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = this.createKey(secretK);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEncrypt = Base64.getDecoder().decode(dataEncrypt);
        byte[] dataDencrypt = cipher.doFinal(bytesEncrypt);
        String data = new String(dataDencrypt);

        return data;
    }

}
