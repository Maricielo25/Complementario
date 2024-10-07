import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Ventas {

    public static void main(String[] args) {
        agregarVenta("Manzana", 10, 19.90, "2024-10-06", 1, "tarjeta");
        obtenerVentas();
    }

    // Método para agregar una venta
    public static void agregarVenta(String producto, int cantidad, double precioTotal, String fecha, int clienteId, String metodoPago) {
        // Conexión a MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("supermercado");
        MongoCollection<Document> collection = database.getCollection("ventas");

        // Creación de documento para la venta
        Document venta = new Document("producto", producto)
                .append("cantidad", cantidad)
                .append("precio_total", precioTotal)
                .append("fecha", fecha)
                .append("cliente_id", clienteId)
                .append("metodo_pago", metodoPago);

        // Insertar el documento en la colección
        collection.insertOne(venta);
        System.out.println("Venta agregada exitosamente");

        // Cerrar la conexión
        mongoClient.close();
    }

    // Método para obtener todas las ventas
    public static void obtenerVentas() {
        // Conexión a MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("supermercado");
        MongoCollection<Document> collection = database.getCollection("ventas");

        // Obtener y mostrar todas las ventas
        for (Document venta : collection.find()) {
            System.out.println(venta.toJson());
        }

        // Cerrar la conexión
        mongoClient.close();
    }
}
