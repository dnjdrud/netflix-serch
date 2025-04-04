package main.com.dnjdrud.feature;

import main.com.dnjdrud.model.User;
import main.com.dnjdrud.service.SearchService;

import java.util.Scanner;

public class SearchUI {

    private final Scanner scanner = new Scanner(System.in);
    private final SearchService searchService = new SearchService();

    public int showMenuAndSelect() {
        System.out.println("\n🎬 NETFLIX 콘텐츠 검색기");
        System.out.println("1. 배우별 검색");
        System.out.println("2. 감독별 검색");
        System.out.println("3. 장르별 검색");
        System.out.println("4. 배우 + 장르");
        System.out.println("5. 감독 + 장르");
        System.out.println("6. 배우 + 감독");
        System.out.println("7. 배우 + 감독 + 장르");
        System.out.println("8. 제목으로 정보 검색 🔎");
        System.out.print("번호 선택: ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public void handleChoice(int choice, User user) {
        switch (choice) {
            case 1 -> searchService.searchByActor(user);
            case 2 -> searchService.searchByDirector(user);
            case 3 -> searchService.searchByGenre(user);
            case 4 -> searchService.searchByActorAndGenre(user);
            case 5 -> searchService.searchByDirectorAndGenre(user);
            case 6 -> searchService.searchByActorAndDirector(user);
            case 7 -> searchService.searchByActorDirectorGenre(user);
            case 8 -> searchService.searchByTitle(user);
            default -> System.out.println("⚠️ 올바른 번호를 선택해주세요.");
        }
    }
}
