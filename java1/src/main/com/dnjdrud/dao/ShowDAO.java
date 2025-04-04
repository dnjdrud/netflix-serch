package main.com.dnjdrud.dao;

import main.com.dnjdrud.db.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowDAO {

    // 1. 배우 이름으로 검색
    public List<String> findShowIdsByActor(String actorName) {
        List<String> showIds = new ArrayList<>();
        String sql = "SELECT l.show_id FROM Link_Cast_Showfact l " +
                "JOIN Casting c ON l.cast_id = c.cast_id " +
                "WHERE c.actor LIKE ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + actorName + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                showIds.add(rs.getString("show_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showIds;
    }

    // 2. 감독 이름으로 검색
    public List<String> findShowIdsByDirector(String directorName) {
        List<String> showIds = new ArrayList<>();
        String sql = "SELECT l.show_id FROM Link_Showfact_Director l " +
                "JOIN Director d ON l.director_id = d.director_id " +
                "WHERE d.director LIKE ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + directorName + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                showIds.add(rs.getString("show_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showIds;
    }

    // 3. 장르로 검색
    public List<String> findShowIdsByGenre(String genre) {
        List<String> showIds = new ArrayList<>();
        String sql = "SELECT l.show_id FROM Link_Showfact_ListedIn l " +
                "JOIN Listed_in li ON l.listed_in_id = li.listed_in_id " +
                "WHERE li.listed_in LIKE ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + genre + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                showIds.add(rs.getString("show_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showIds;
    }

    // 4. 배우 + 장르 검색
    public List<String> findShowIdsByActorAndGenre(String actorName, String genre) {
        List<String> showIds = new ArrayList<>();
        String sql = "SELECT DISTINCT lcs.show_id FROM Link_Cast_Showfact lcs " +
                "JOIN Casting c ON lcs.cast_id = c.cast_id " +
                "JOIN Link_Showfact_ListedIn lsi ON lcs.show_id = lsi.show_id " +
                "JOIN Listed_in li ON lsi.listed_in_id = li.listed_in_id " +
                "WHERE c.actor LIKE ? AND li.listed_in LIKE ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + actorName + "%");
            pstmt.setString(2, "%" + genre + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                showIds.add(rs.getString("show_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showIds;
    }

    // 5. 감독 + 장르 검색
    public List<String> findShowIdsByDirectorAndGenre(String directorName, String genre) {
        List<String> showIds = new ArrayList<>();
        String sql = "SELECT DISTINCT lsd.show_id FROM Link_Showfact_Director lsd " +
                "JOIN Director d ON lsd.director_id = d.director_id " +
                "JOIN Link_Showfact_ListedIn lsi ON lsd.show_id = lsi.show_id " +
                "JOIN Listed_in li ON lsi.listed_in_id = li.listed_in_id " +
                "WHERE d.director LIKE ? AND li.listed_in LIKE ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + directorName + "%");
            pstmt.setString(2, "%" + genre + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                showIds.add(rs.getString("show_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showIds;
    }

    // 6. 배우 + 감독 검색
    public List<String> findShowIdsByActorAndDirector(String actorName, String directorName) {
        List<String> showIds = new ArrayList<>();
        String sql = "SELECT DISTINCT lcs.show_id FROM Link_Cast_Showfact lcs " +
                "JOIN Casting c ON lcs.cast_id = c.cast_id " +
                "JOIN Link_Showfact_Director lsd ON lcs.show_id = lsd.show_id " +
                "JOIN Director d ON lsd.director_id = d.director_id " +
                "WHERE c.actor LIKE ? AND d.director LIKE ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + actorName + "%");
            pstmt.setString(2, "%" + directorName + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                showIds.add(rs.getString("show_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showIds;
    }

    // 7. 배우 + 감독 + 장르 검색
    public List<String> findShowIdsByActorDirectorGenre(String actorName, String directorName, String genre) {
        List<String> showIds = new ArrayList<>();
        String sql = "SELECT DISTINCT lcs.show_id FROM Link_Cast_Showfact lcs " +
                "JOIN Casting c ON lcs.cast_id = c.cast_id " +
                "JOIN Link_Showfact_Director lsd ON lcs.show_id = lsd.show_id " +
                "JOIN Director d ON lsd.director_id = d.director_id " +
                "JOIN Link_Showfact_ListedIn lsi ON lcs.show_id = lsi.show_id " +
                "JOIN Listed_in li ON lsi.listed_in_id = li.listed_in_id " +
                "WHERE c.actor LIKE ? AND d.director LIKE ? AND li.listed_in LIKE ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + actorName + "%");
            pstmt.setString(2, "%" + directorName + "%");
            pstmt.setString(3, "%" + genre + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                showIds.add(rs.getString("show_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showIds;
    }

    // 8. 제목으로 검색
    public List<String> findShowIdsByTitle(String title) {
        List<String> showIds = new ArrayList<>();
        String sql = "SELECT show_id FROM ShowMain WHERE title LIKE ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + title + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                showIds.add(rs.getString("show_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showIds;
    }

    // 상세 정보 출력
    public void printShowDetails(String showId) {
        String sql = "SELECT sm.title, sf.type, sf.duration, sf.country, sf.date_added, " +
                "sf.released_year, sf.description " +
                "FROM Showfact sf " +
                "JOIN ShowMain sm ON sf.show_id = sm.show_id " +
                "WHERE sf.show_id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, showId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n🔍 [상세 정보]");
                System.out.println("🎞 제목: " + rs.getString("title"));
                System.out.println("📺 타입: " + rs.getString("type"));
                System.out.println("⏱ 길이: " + rs.getString("duration"));
                System.out.println("🌍 국가: " + rs.getString("country"));
                System.out.println("📅 등록일: " + rs.getString("date_added"));
                System.out.println("📆 출시년도: " + rs.getString("released_year"));
                System.out.println("📝 설명: " + rs.getString("description"));
            } else {
                System.out.println("❌ 콘텐츠를 찾을 수 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
