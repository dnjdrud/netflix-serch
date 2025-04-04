package main.com.dnjdrud.service;

import main.com.dnjdrud.dao.ShowDAO;
import main.com.dnjdrud.model.User;

import java.util.List;
import java.util.Scanner;

public class SearchService {

    private final Scanner scanner = new Scanner(System.in);
    private final ShowDAO showDAO = new ShowDAO();
    private final PreferenceService preferenceService = new PreferenceService();

    public void searchByActor(User user) {
        System.out.print("🎭 검색할 배우 이름을 입력해주세요: ");
        String actor = scanner.nextLine().trim();
        List<String> showIds = showDAO.findShowIdsByActor(actor);
        displayAndStoreResults(showIds, user);
    }

    public void searchByDirector(User user) {
        System.out.print("🎬 검색할 감독 이름을 입력해주세요: ");
        String director = scanner.nextLine().trim();
        List<String> showIds = showDAO.findShowIdsByDirector(director);
        displayAndStoreResults(showIds, user);
    }

    public void searchByGenre(User user) {
        System.out.print("📂 검색할 장르를 입력해주세요: ");
        String genre = scanner.nextLine().trim();
        List<String> showIds = showDAO.findShowIdsByGenre(genre);
        displayAndStoreResults(showIds, user);
    }

    public void searchByActorAndGenre(User user) {
        System.out.print("🎭 배우 이름: ");
        String actor = scanner.nextLine().trim();
        System.out.print("📂 장르 이름: ");
        String genre = scanner.nextLine().trim();
        List<String> showIds = showDAO.findShowIdsByActorAndGenre(actor, genre);
        displayAndStoreResults(showIds, user);
    }

    public void searchByDirectorAndGenre(User user) {
        System.out.print("🎬 감독 이름: ");
        String director = scanner.nextLine().trim();
        System.out.print("📂 장르 이름: ");
        String genre = scanner.nextLine().trim();
        List<String> showIds = showDAO.findShowIdsByDirectorAndGenre(director, genre);
        displayAndStoreResults(showIds, user);
    }

    public void searchByActorAndDirector(User user) {
        System.out.print("🎭 배우 이름: ");
        String actor = scanner.nextLine().trim();
        System.out.print("🎬 감독 이름: ");
        String director = scanner.nextLine().trim();
        List<String> showIds = showDAO.findShowIdsByActorAndDirector(actor, director);
        displayAndStoreResults(showIds, user);
    }

    public void searchByActorDirectorGenre(User user) {
        System.out.print("🎭 배우 이름: ");
        String actor = scanner.nextLine().trim();
        System.out.print("🎬 감독 이름: ");
        String director = scanner.nextLine().trim();
        System.out.print("📂 장르 이름: ");
        String genre = scanner.nextLine().trim();
        List<String> showIds = showDAO.findShowIdsByActorDirectorGenre(actor, director, genre);
        displayAndStoreResults(showIds, user);
    }

    public void searchByTitle(User user) {
        System.out.print("🔤 검색할 작품 제목을 입력해주세요: ");
        String title = scanner.nextLine().trim();
        List<String> showIds = showDAO.findShowIdsByTitle(title);
        displayAndStoreResults(showIds, user);
    }

    // 공통 메서드
    private void displayAndStoreResults(List<String> showIds, User user) {
        if (showIds.isEmpty()) {
            System.out.println("❌ 해당 조건에 맞는 콘텐츠가 없습니다.");
            return;
        }

        for (String showId : showIds) {
            showDAO.printShowDetails(showId);
            System.out.print("\n이 콘텐츠를 선호 정보에 저장하시겠습니까? (YES/NO): ");
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("YES")) {
                preferenceService.savePreferenceByShow(showId, user.getUserId());
                System.out.println("✅ 저장 완료!");
            }
        }
    }
}
