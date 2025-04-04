package main.com.dnjdrud.service;

import main.com.dnjdrud.dao.UserPreferenceDAO;
import main.com.dnjdrud.db.DBConfig;

import java.sql.*;
import java.util.List;

public class PreferenceService {
    private final UserPreferenceDAO prefDAO = new UserPreferenceDAO();

    public void printUserPreferences(main.com.dnjdrud.model.User user) {
        List<String> directors = prefDAO.getPreferredDirectors(user.getUserId());
        List<String> casts = prefDAO.getPreferredCasts(user.getUserId());
        List<String> genres = prefDAO.getPreferredGenres(user.getUserId());

        System.out.println(user.getUsername() + "님 안녕하세요!");

        boolean hasPreference = !directors.isEmpty() || !casts.isEmpty() || !genres.isEmpty();

        if (hasPreference) {
            System.out.println(user.getUsername() + "님이 좋아하는 배우: " + String.join(", ", casts));
            System.out.println(user.getUsername() + "님이 좋아하는 감독: " + String.join(", ", directors));
            System.out.println(user.getUsername() + "님이 좋아하는 장르: " + String.join(", ", genres));
        } else {
            System.out.println(user.getUsername() + "님, 아직 사용 기록이 없으시군요!");
            System.out.println("선호하시는 감독, 배우, 장르를 편하게 검색해보세요!");
        }
    }

    public void savePreferenceByShow(String showId, int userId) {
        try (Connection conn = DBConfig.getConnection()) {
            Integer directorId = null;
            String sql1 = "SELECT director_id FROM Link_Showfact_Director WHERE show_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
                pstmt.setString(1, showId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) directorId = rs.getInt("director_id");
            }

            Integer castId = null;
            String sql2 = "SELECT cast_id FROM Link_Cast_Showfact WHERE show_id = ? LIMIT 1";
            try (PreparedStatement pstmt = conn.prepareStatement(sql2)) {
                pstmt.setString(1, showId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) castId = rs.getInt("cast_id");
            }

            Integer genreId = null;
            String sql3 = "SELECT listed_in_id FROM Link_Showfact_ListedIn WHERE show_id = ? LIMIT 1";
            try (PreparedStatement pstmt = conn.prepareStatement(sql3)) {
                pstmt.setString(1, showId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) genreId = rs.getInt("listed_in_id");
            }

            prefDAO.insertPreference(userId, directorId, castId, genreId);
            System.out.println("📌 선호 정보가 저장되었습니다!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
