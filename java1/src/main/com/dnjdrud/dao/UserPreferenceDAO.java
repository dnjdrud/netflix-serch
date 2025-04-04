package main.com.dnjdrud.dao;

import main.com.dnjdrud.db.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPreferenceDAO {

    public List<String> getPreferredDirectors(int userId) {
        String sql = "SELECT d.director FROM UserPreference up " +
                "JOIN Director d ON up.director_id = d.director_id " +
                "WHERE up.user_id = ? AND up.director_id IS NOT NULL";
        return getListBySQL(sql, userId);
    }

    public List<String> getPreferredCasts(int userId) {
        String sql = "SELECT c.actor FROM UserPreference up " +
                "JOIN Casting c ON up.cast_id = c.cast_id " +
                "WHERE up.user_id = ? AND up.cast_id IS NOT NULL";
        return getListBySQL(sql, userId);
    }

    public List<String> getPreferredGenres(int userId) {
        String sql = "SELECT l.listed_in FROM UserPreference up " +
                "JOIN Listed_in l ON up.listed_in_id = l.listed_in_id " +
                "WHERE up.user_id = ? AND up.listed_in_id IS NOT NULL";
        return getListBySQL(sql, userId);
    }

    private List<String> getListBySQL(String sql, int userId) {
        List<String> result = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void insertPreference(int userId, Integer directorId, Integer castId, Integer genreId) {
        String sql = "INSERT INTO UserPreference (user_id, director_id, cast_id, listed_in_id, created_at) " +
                "VALUES (?, ?, ?, ?, NOW())";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            if (directorId != null) pstmt.setInt(2, directorId); else pstmt.setNull(2, Types.INTEGER);
            if (castId != null) pstmt.setInt(3, castId); else pstmt.setNull(3, Types.INTEGER);
            if (genreId != null) pstmt.setInt(4, genreId); else pstmt.setNull(4, Types.INTEGER);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
