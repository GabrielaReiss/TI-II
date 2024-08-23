package maven.demo;

import java.sql.*;

public class DAO {
    private Connection conexao;

    public DAO() {
        conexao = null;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";                    
        String serverName = "localhost";
        String mydatabase = "teste";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
        String username = "postgres";
        String password = "senha";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao != null);
            System.out.println("Conexão efetuada com o postgres!");
        } catch (ClassNotFoundException e) { 
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean inserirLivro(Livro livro) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO livro (id, nome, paginas) "
                           + "VALUES (" + livro.getId() + ", '" + livro.getNome() + "', "  
                           + livro.getQuantidadeDePaginas() + ");");
            st.close();
            status = true;
        } catch (SQLException e) {  
            System.err.println("Erro ao inserir livro: " + e.getMessage());
        }
        return status;
    }

    public boolean atualizarLivro(Livro livro) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE livro SET nome = '" + livro.getNome() + "', paginas = "  
                       + livro.getQuantidadeDePaginas() + " WHERE id = " + livro.getId();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException e) {  
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
        }
        return status;
    }

    public boolean excluirLivro(int id) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM livro WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException e) {  
            System.err.println("Erro ao excluir livro: " + e.getMessage());
        }
        return status;
    }

    public Livro[] getLivros() {
        Livro[] livros = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM livro");        
            if (rs.next()) {
                rs.last();
                livros = new Livro[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    livros[i] = new Livro(rs.getInt("id"), rs.getString("nome"), rs.getInt("paginas"));
                }
            }
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        }
        return livros;
    }
}