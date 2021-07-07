package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import domain.BST.BST;
import domain.BST.TreeException;
import domain.graph.AdjacencyListGraph;
import domain.graph.GraphException;
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
import sun.security.provider.certpath.AdjacencyList;

/**
 *
 * @author PaoVega
 */
public class Files {

    int counter = 1;

    public boolean existFile(String address) {
        File file = new File(address);
        return file.exists();
    }

    public void addFood(Food food) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("foodFile.txt", true));//no sobreescibe
        try {
            bw.write(food.getAutoID() + "," + food.getName() + "," + food.getPrice() + "," + food.getRestaurantID());
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
        String treeData1[] = new String[50];
        treeData = bst.preOrderAux().split("\n");

        for (int i = 0; i < bst.size(); i++) {
            treeData1 = treeData[i].split(",");
            result += i + 1 + "," + treeData1[1] + "," + treeData1[2] + "," + treeData1[3] + "\n";
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("foodFile.txt"));//sobreescibe

        String foods[] = result.split("\n");

        for (int i = 0; i < treeData.length; i++) {
            bw.write(foods[i]);
            bw.newLine();
            bw.flush();
        }
        bw.close();
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
                Food food = new Food((String) foodS[1], Double.parseDouble((String) foodS[2]), Integer.parseInt((String) foodS[3])); //Carga las comidas del txt
                tree.add(food);
                linea = br.readLine();
            }
        }//Fin linea+
        br.close();
        return tree;
    }

    public void addProduct(Product product) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("productFile.txt", true));//no sobreescibe
        try {
            bw.write(product.getAutoID() + "," + product.getName() + "," + product.getPrice() + "," + product.getSupermarketID());
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyProduct(BST bst) throws IOException, TreeException {

        String result = "";

        String treeData[] = new String[bst.size()];
        String treeData1[] = new String[50];
        treeData = bst.preOrderAux().split("\n");

        for (int i = 0; i < bst.size(); i++) {
            treeData1 = treeData[i].split(",");
            result += i + 1 + "," + treeData1[1] + "," + treeData1[2] + "," + treeData1[3] + "\n";
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("productFile.txt"));//sobreescibe

        String products[] = result.split("\n");

        for (int i = 0; i < treeData.length; i++) {
            bw.write(products[i]);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

    public BST loadProductsBSTrees(String fileDir, BST tree) throws IOException {
        if (!existFile(fileDir)) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("productFile.txt", true));
        }

        BufferedReader br = new BufferedReader(new FileReader(fileDir));
        String linea = br.readLine();

        if (linea != null) {
            while (linea != null) {
                Object products[] = linea.split(",");//Divide la linea en un array de String
                //Carga las comidas del txt
                Product product = new Product((String) products[1], Double.parseDouble((String) products[2]), Integer.parseInt((String) products[3]));
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

    public void addRestaurant(Restaurant restaurant) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("restaurantsFile.txt", true));//no sobreescibe
        try {
            bw.write(counter + "," + restaurant.getName() + "," + restaurant.getLocation());
            counter++;
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMarket(Supermarket superMarket) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("superMarketsFile.txt", true));//no sobreescibe
        try {
            bw.write(counter + "," + superMarket.getName() + "," + superMarket.getLocation());
            counter++;
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public AdjacencyListGraph loadRestaurants(String fileDir, AdjacencyListGraph list) throws IOException, GraphException, ListException {
        if (!existFile(fileDir)) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("restaurantsFile.txt", true));
        }

        BufferedReader br = new BufferedReader(new FileReader(fileDir));
        String linea = br.readLine();

        if (linea != null) {
            while (linea != null) {
                String restaurants[] = linea.split(",");//Divide la linea en un array de String
                Restaurant restaurant = new Restaurant(restaurants[1], restaurants[2]); //Carga las comidas del txt
                list.addVertex(restaurant);
                linea = br.readLine();
            }
        }//Fin linea+
        br.close();
        return list;
    }

    public AdjacencyListGraph loadSuperMarkets(String fileDir, AdjacencyListGraph list) throws IOException, GraphException, ListException {
        if (!existFile(fileDir)) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("superMarketsFile.txt", true));
        }

        BufferedReader br = new BufferedReader(new FileReader(fileDir));
        String linea = br.readLine();

        if (linea != null) {
            while (linea != null) {
                String superMarkets[] = linea.split(",");//Divide la linea en un array de String
                Supermarket superMarket = new Supermarket(superMarkets[1], superMarkets[2]); //Carga las comidas del txt
                list.addVertex(superMarket);
                linea = br.readLine();
            }
        }//Fin linea+
        br.close();
        return list;
    }

    public void controlLoadRS() throws IOException, GraphException, ListException {
        loadRestaurants("restaurantsFile.txt", util.Utility.getGraphList());
        loadSuperMarkets("superMarketsFile.txt", util.Utility.getGraphList());
    }

    public void modifyRestaurant() throws IOException, ListException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("restaurantsFile.txt", true));//no sobreescibe
        try {
            for (int i = 1; i < util.Utility.getGraphList().size(); i++) {
                Restaurant restaurant = new Restaurant(((Restaurant)util.Utility.getGraphList().getVertexByIndex(i)
                        .data).getName(), ((Restaurant)util.Utility.getGraphList().getVertexByIndex(i)
                        .data).getLocation());
                bw.write(counter + "," + restaurant.getName() + "," + restaurant.getLocation());
                counter++;
                bw.newLine();
                bw.flush();
                bw.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
