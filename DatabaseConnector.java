import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/forcaDB";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void salvarResultado(String letrasChutadas, String palavraEscolhida, int tentativasNecessarias) {
        String sql = "INSERT INTO jogo_da_forca_resultados (letras_chutadas, palavra_escolhida, tentativas_necessarias) VALUES (?, ?, ?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, letrasChutadas);
            stmt.setString(2, palavraEscolhida);
            stmt.setInt(3, tentativasNecessarias);
            stmt.executeUpdate();
            System.out.println("Resultado salvo com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o resultado: " + e.getMessage());
        }
    }
}

