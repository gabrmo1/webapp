package br.com.webapp.dao;


import br.com.webapp.model.Genero;
import br.com.webapp.model.Procedimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProcedimentoDAO implements IProcedimentoDAO {

    private final Connection connection;

    public ProcedimentoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Procedimento save(Procedimento procedimento) {

        try {
            String sql = "INSERT INTO Procedimento (nroProcedimento, idade, genero, permitido) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, procedimento.getNroProcedimento());
            preparedStatement.setInt(2, procedimento.getIdade());
            preparedStatement.setString(3, procedimento.getGenero().toString());
            preparedStatement.setBoolean(4, procedimento.getPermitido());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return procedimento;
    }

    @Override
    public Procedimento update(Procedimento procedimento) {
        try {
            String sql = "UPDATE Procedimento SET nroProcedimento = ?, idade = ?, genero = ?, permitido = ? WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, procedimento.getNroProcedimento());
            preparedStatement.setInt(2, procedimento.getIdade());
            preparedStatement.setString(3, procedimento.getGenero().toString());
            preparedStatement.setBoolean(4, procedimento.getPermitido());
            preparedStatement.setLong(5, procedimento.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return procedimento;
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM Procedimento WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Procedimento> findAll() {
        String sql = "SELECT id, nroProcedimento, idade, genero, permitido FROM Procedimento";

        List<Procedimento> procedimentos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nroProcedimento = rs.getString("nroProcedimento");
                Integer idade = rs.getInt("idade");
                Genero genero = Genero.valueOf(rs.getString("genero"));
                Boolean permitido = rs.getBoolean("permitido");

                Procedimento procedimento = new Procedimento(id, nroProcedimento, idade, genero, permitido);
                procedimentos.add(procedimento);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return procedimentos;
    }

    public List<Procedimento> findByParams(String nroProcedimento, Integer idade, Genero genero) {
        String sql = "SELECT id, nroProcedimento, idade, genero, permitido FROM Procedimento p " +
                "WHERE p.nroProcedimento = ? " +
                "AND p.idade = ? " +
                "AND p.genero = ? ";

        List<Procedimento> procedimentos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, nroProcedimento);
            preparedStatement.setInt(2, idade);
            preparedStatement.setString(3, genero.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Procedimento procedimento = new Procedimento(
                        rs.getInt("id"),
                        rs.getString("nroProcedimento"),
                        rs.getInt("idade"),
                        Genero.valueOf(rs.getString("genero")),
                        rs.getBoolean("permitido"));

                procedimentos.add(procedimento);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return procedimentos;
    }

    @Override
    public Optional<Procedimento> findById(Long id) {
        String sql = "SELECT id, nroProcedimento, idade, genero, permitido FROM Procedimento WHERE id = ?";

        Procedimento procedimento = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer pKey = rs.getInt("id");
                String nroProcedimento = rs.getString("nroProcedimento");
                Integer idade = rs.getInt("idade");
                Genero genero = Genero.valueOf(rs.getString("genero"));
                Boolean permitido = rs.getBoolean("permitido");

                procedimento = new Procedimento(pKey, nroProcedimento, idade, genero, permitido);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(procedimento);
    }
}
