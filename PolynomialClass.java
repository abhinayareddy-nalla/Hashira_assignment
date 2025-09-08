import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.math.BigInteger;
import org.json.JSONObject;

public class PolynomialClass {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Please provide the JSON filename as argument.");
            return;
        }

        String jsonStr = new String(Files.readAllBytes(Paths.get(args[0])));
        JSONObject obj = new JSONObject(jsonStr);

        int n = obj.getJSONObject("keys").getInt("n");
        int k = obj.getJSONObject("keys").getInt("k");

        List<BigInteger> roots = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!obj.has(String.valueOf(i))) continue;
            JSONObject rootObj = obj.getJSONObject(String.valueOf(i));
            int base = Integer.parseInt(rootObj.getString("base"));
            String val = rootObj.getString("value");
            BigInteger root = new BigInteger(val, base);
            roots.add(root);
        }

        List<BigInteger> chosen = roots.subList(0, k);

        
        BigInteger[] coeffs = { BigInteger.ONE };
        for (BigInteger r : chosen) {
            BigInteger[] newCoeff = new BigInteger[coeffs.length + 1];
            Arrays.fill(newCoeff, BigInteger.ZERO);
            for (int j = 0; j < coeffs.length; j++) {
                newCoeff[j] = newCoeff[j].add(coeffs[j]); 
                newCoeff[j + 1] = newCoeff[j + 1].subtract(coeffs[j].multiply(r)); 
            }
            coeffs = newCoeff;
        }

        System.out.println("Polynomial Coefficients:");
        for (BigInteger c : coeffs) {
            System.out.println(c + " ");
        }
    }
}
