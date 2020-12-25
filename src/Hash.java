import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Hash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		int[] answer;
		answer = solution(genres,plays);
		for (int i : answer) {
			System.out.println(i);
		}
	}

	/*
	 * ���α׷��ӽ� �ؽ� ���� 02
	 */
	public static boolean solution(String[] phone_book) {
		boolean answer = true;
		if (phone_book.length == 1)
			return answer;

		Arrays.sort(phone_book);
		int i = 0;
		while (i < phone_book.length - 1) {
			if (phone_book[i].length() > phone_book[i + 1].length()) {
				i++;
				continue;
			}
			System.out.println(phone_book[i].substring(0, phone_book[i].length()));
			if (phone_book[i].equals(phone_book[i + 1].substring(0, phone_book[i].length()))) {
				return false;
			}
			i++;
		}
		return answer;
	}

	/*
	 * ���α׷��ӽ� �ؽ� ���� 03
	 */
	public static int solution(String[][] clothes) {
		int answer = 1;
		HashMap<String, Integer> myHashMap = new HashMap<>();

		for (String[] strings : clothes) {
			myHashMap.put(strings[1], myHashMap.getOrDefault(strings[1], 0) + 1);
		}
		System.out.println(myHashMap.toString());
		for (String string : myHashMap.keySet()) {
			answer *= (myHashMap.get(string) + 1);
		}
		answer -= 1;
		return answer;
	}

	/*
	 * ���α׷��ӽ� �ؽ� ���� 04
	 */
	public static int[] solution(String[] genres, int[] plays) {
		int[] answer = {};
		ArrayList<Integer> answerList = new ArrayList<>();
		HashMap<String, GenresDetail> genresHashMap = new HashMap<>();
		String tempGen;
		int tempPlay;
		ArrayList<String> sortedGenres = new ArrayList<>();	// �������� �帣��

		for (var i = 0; i < genres.length; i++) {
			tempGen = genres[i];
			tempPlay = plays[i];
			// ó�� ������ �帣�̸� �� GenresDetail �߰�
			if (!genresHashMap.containsKey(tempGen))
				genresHashMap.put(tempGen, new GenresDetail(tempPlay, i));
			else {
				genresHashMap.get(tempGen).setDetail(plays, i);
			}
		}

		// �帣�� playtime �� �� ������ �����Ͽ� sortedGenres�� ����
		for (String key : genresHashMap.keySet()) {
			if(sortedGenres.isEmpty()) {
				sortedGenres.add(key);
				continue;
			}
			for (var i = 0; i < sortedGenres.size() ; i++) {
				//�������� �帣�� list�� �帣�� map�� �ִ� �帣�� ���� playtime �����ͼ� ��
				//���ο�� �� ũ�� ���ο�� �տ� ����
				if(genresHashMap.get(sortedGenres.get(i)).playtime < genresHashMap.get(key).playtime) {
					sortedGenres.add(i, key);
					continue;
				}
			}
			if(!sortedGenres.contains(key)) {
				sortedGenres.add(key);
			}
		}
		GenresDetail tempGenresDetail = null;
		for (int i = 0; i < sortedGenres.size(); i++) {
			tempGenresDetail = genresHashMap.get(sortedGenres.get(i));
			answerList.add(tempGenresDetail.song1);
			if(tempGenresDetail.song2 != -1) answerList.add(tempGenresDetail.song2);
		}
		answer = new int[answerList.size()];
		for (int i = 0; i < answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}
		return answer;
	}

	private static class GenresDetail {
		public long playtime;
		public int song1;
		public int song2;

		public GenresDetail() {
			// TODO Auto-generated constructor stub
			super();

		}

		public GenresDetail(long playtime, int song1) {
			// TODO Auto-generated constructor stub
			super();
			this.playtime = playtime;
			this.song1 = song1;
			this.song2 = -1;

		}

		public void setDetail(int[] plays, int i) {
			this.playtime += plays[i];
			if (plays[song1] < plays[i]) {
				this.song2 = this.song1;
				this.song1 = i;
			} else if (song2!= -1 && plays[song2] < plays[i]) {
				this.song2 = i;
			}

		}
	}



}
