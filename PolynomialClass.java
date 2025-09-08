import java.math.BigInteger;
import java.util.*;
import java.io.*;
import com.google.gson.*;

public class PolynomialClass{

    public static void main(String[] args) throws Exception {
        // Read JSON from file if argument is given, else from stdin
        String jsonStr;
        if (args.length > 0) {
            jsonStr = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(args[0])));
        } else {
            StringBuilder sb = new StringBuilder();
            try (Scanner sc = new Scanner(System.in)) {
                while (sc.hasNextLine()) {
                    sb.append(sc.nextLine());
                }
            }
            jsonStr = sb.toString();
        }

        // Parse JSON
        JsonObject obj = JsonParser.parseString(jsonStr).getAsJsonObject();
        JsonObject keys = obj.getAsJsonObject("keys");
        int n = keys.get("n").getAsInt();
        int k = keys.get("k").getAsInt();

        // Collect first k roots
        List<BigInteger> roots = new ArrayList<>();
        for (int i = 1; i <= n && roots.size() < k; i++) {
            if (obj.has(String.valueOf(i))) {
                JsonObject rootObj = obj.getAsJsonObject(String.valueOf(i));
                int base = rootObj.get("base").getAsInt();
                String valueStr = rootObj.get("value").getAsString();
                BigInteger root = new BigInteger(valueStr, base);
                roots.add(root);
            }
        }

        // Compute coefficients of polynomial from roots
        BigInteger[] coeffs = new BigInteger[roots.size() + 1];
        Arrays.fill(coeffs, BigInteger.ZERO);
        coeffs[0] = BigInteger.ONE;

        for (BigInteger root : roots) {
            BigInteger[] newCoeffs = new BigInteger[coeffs.length];
            Arrays.fill(newCoeffs, BigInteger.ZERO);

            for (int i = 0; i < coeffs.length - 1; i++) {
                newCoeffs[i] = newCoeffs[i].add(coeffs[i].negate().multiply(root));
                newCoeffs[i + 1] = newCoeffs[i + 1].add(coeffs[i]);
            }
            coeffs = newCoeffs;
        }

        // Prepare JSON output
        JsonObject result = new JsonObject();
        JsonArray coeffArray = new JsonArray();
        for (BigInteger c : coeffs) {
            coeffArray.add(c.toString());
        }
        result.add("coefficients", coeffArray);

        // Print result in JSON format
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(result));
    }
}
