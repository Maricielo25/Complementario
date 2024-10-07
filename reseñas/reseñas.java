import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Resenas {

    public static void main(String[] args) {
        agregarResena(1, "Manzana", "Muy frescas y deliciosas", 5, 1);
        obtenerResenas();
    }

    // Método para agregar una reseña
    public static void agregarResena(int ventaId, String producto, String resenaTexto, int calificacion, int clienteId) {
        // Conexión a MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("supermercado");
        MongoCollection<Document> collection = database.getCollection("resenas");

        // Creación de documento para la reseña
        Document resena = new Document("venta_id", ventaId)
                .append("producto", producto)
                .append("resena_texto", resenaTexto)
                .append("calificacion", calificacion)
                .append("cliente_id", clienteId);

        // Insertar el documento en la colección
        collection.insertOne(resena);
        System.out.println("Reseña agregada exitosamente");

        // Cerrar la conexión
        mongoClient.close();
    }

    // Método para obtener todas las reseñas
    public static void obtenerResenas() {
        // Conexión a MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("supermercado");
        MongoCollection<Document> collection = database.getCollection("resenas");

        // Obtener y mostrar todas las reseñas
        for (Document resena : collection.find()) {
            System.out.println(resena.toJson());
        }

        // Cerrar la conexión
        mongoClient.close();
    }
}
