import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.QueryResults;
import javafx.stage.Screen;
import net.bytebuddy.asm.Advice;
import org.hibernate.internal.build.AllowSysOut;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        num7_data();
//        System.out.println("=============================================");
//        num7();
        showTicketingList(1);

        try {
            tx.begin();



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    public static void addData() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            // 사용자
            tx.begin();
            User user1 = new User("이준재", 22, "원주시", "남산로", "22932");
            em.persist(user1);
            tx.commit();

            // 영화
            tx.begin();
            Movie movie1 = new Movie("아이언맨", LocalDate.now(), Genre.Action, 120);
            em.persist(movie1);
            Movie movie2 = new Movie("타짜", LocalDate.now(), Genre.Musical, 120);
            em.persist(movie2);
            Movie movie3 = new Movie("기생충", LocalDate.now(), Genre.Adventure, 130);
            em.persist(movie3);
            Movie movie4 = new Movie("해리 포터와 마법사의 돌", LocalDate.now(), Genre.Adventure, 140);
            em.persist(movie4);
            tx.commit();

            // 감독
            tx.begin();
            Director director1 = new Director("크리스 콜럼버스", LocalDate.of(2000, 3, 20), "미국");
            em.persist(director1);
            Director director2 = new Director("류승완", LocalDate.of(1973, 12, 15), "한국");
            em.persist(director2);
            Director director3 = new Director("봉준호", LocalDate.of(1969, 9, 14), "한국");
            em.persist(director3);
            tx.commit();

            // 배우
            tx.begin();
            Actor actor1 = new Actor("최범휘", LocalDate.of(1997, 9, 30), 173, "angel_bum");
            em.persist(actor1);
            Actor actor2 = new Actor("김현수", LocalDate.of(2000, 3, 7), 165, "kimchi_mine");
            em.persist(actor2);
            Actor actor3 = new Actor("엄소정", LocalDate.of(2000, 9, 7), 164, "cute444");
            em.persist(actor3);
            Actor actor4 = new Actor("이재준", LocalDate.of(1998, 3, 13), 175, "now_juni");
            em.persist(actor4);
            Actor actor5 = new Actor("한현택", LocalDate.of(1998, 5, 6), 186, "yogurt_boy");
            em.persist(actor5);
            Actor actor6 = new Actor("최연웅", LocalDate.of(1998, 8, 20), 175, "yes_yes");
            em.persist(actor6);
            tx.commit();



            // 감독 배우 설정
            Movie movieArray[] = {movie1,movie2,movie3,movie4};
            Actor actors[] ={actor1,actor2,actor3,actor4,actor5,actor6};
            Director directors[] = {director1,director2,director3};

            tx.begin();
            movie1.setDirector(director1);
            em.persist(movie1);
            tx.commit();

            tx.begin();
            Participation participation1 = new Participation(movie1, actor1, "주연");
            System.out.println(participation1.getRule() + "##############################");
            em.persist(participation1);
            Participation participation2 = new Participation(movie1, actor2, "주연");
            em.persist(participation2);
            Participation participation3 = new Participation(movie1, actor3, "조연");
            em.persist(participation3);
            Participation participation4 = new Participation(movie1, actor4, "조연");
            em.persist(participation4);
            tx.commit();

            tx.begin();
            Participation participation5 = new Participation(movie1, actor5, "조연");
            em.persist(participation5);

            movie2.setDirector(director2);
            Participation participation11 = new Participation(movie2, actor2, "주연");
            System.out.println(participation11.getRule() + "##############################");
            em.persist(participation11);
            Participation participation22 = new Participation(movie2, actor3, "주연");
            em.persist(participation22);
            Participation participation33 = new Participation(movie2, actor4, "조연");
            em.persist(participation33);
            Participation participation44 = new Participation(movie2, actor5, "조연");
            em.persist(participation44);
            Participation participation55 = new Participation(movie2, actor6, "조연");
            em.persist(participation55);

            movie3.setDirector(director3);
            Participation participation111 = new Participation(movie3, actor3, "주연");
            System.out.println(participation111.getRule() + "##############################");
            em.persist(participation111);
            Participation participation222 = new Participation(movie3, actor4, "주연");
            em.persist(participation222);
            Participation participation333 = new Participation(movie3, actor5, "조연");
            em.persist(participation333);
            Participation participation444 = new Participation(movie3, actor6, "조연");
            em.persist(participation444);
            Participation participation555 = new Participation(movie3, actor1, "조연");
            em.persist(participation555);

            movie4.setDirector(director1);
            Participation participation1111 = new Participation(movie4, actor4, "주연");
            System.out.println(participation1111.getRule() + "##############################");
            em.persist(participation1111);
            Participation participation2222 = new Participation(movie4, actor5, "주연");
            em.persist(participation2222);
            Participation participation3333 = new Participation(movie4, actor6, "조연");
            em.persist(participation3333);
            Participation participation4444 = new Participation(movie4, actor1, "조연");
            em.persist(participation4444);
            Participation participation5555 = new Participation(movie4, actor2, "조연");
            em.persist(participation5555);
            tx.commit();


            // 상영관
            tx.begin();
            Theater theater1 = new Theater("1관", 1);
            em.persist(theater1);
            Theater theater2 = new Theater("2관", 2);
            em.persist(theater2);
            tx.commit();

            // 상영일정
            tx.begin();
            Screening screening1 = new Screening(LocalDateTime.of(2019,11,12,13,15,0),LocalDateTime.of(2019,11,12,15,15,0));
            screening1.setMovie(movie1);
            screening1.setTheater(theater1);
            em.persist(screening1);
            Screening screening2 = new Screening(LocalDateTime.of(2019,11,12,15,10,0),LocalDateTime.of(2019,11,12,17,10,0));
            screening2.setMovie(movie1);
            screening2.setTheater(theater1);
            em.persist(screening2);
            Screening screening3 = new Screening(LocalDateTime.of(2019,11,12,11,30,0),LocalDateTime.of(2019,11,12,12,30,0));
            screening3.setMovie(movie1);
            screening3.setTheater(theater1);
            em.persist(screening3);

            Screening screening4 = new Screening(LocalDateTime.of(2019,11,12,13,10,0),LocalDateTime.of(2019,11,12,15,10,0));
            screening4.setMovie(movie2);
            screening4.setTheater(theater2);
            em.persist(screening4);
            Screening screening5 = new Screening(LocalDateTime.of(2000,11,12,10,10,0),LocalDateTime.of(2000,11,12,12,10,0));
            screening5.setMovie(movie2);
            screening5.setTheater(theater2);
            em.persist(screening5);
            Screening screening6 = new Screening(LocalDateTime.of(2000,11,12,17,15,0),LocalDateTime.of(2000,11,12,19,15,0));
            screening6.setMovie(movie2);
            screening6.setTheater(theater2);
            em.persist(screening6);

            Screening screening7 = new Screening(LocalDateTime.of(2021,9,12,13,15,0),LocalDateTime.of(2021,9,12,15,15,0));
            screening7.setMovie(movie2);
            screening7.setTheater(theater2);
            em.persist(screening7);
            Screening screening8 = new Screening(LocalDateTime.of(2021,11,9,10,15,0),LocalDateTime.of(2021,11,9,12,15,0));
            screening8.setMovie(movie2);
            screening8.setTheater(theater2);
            em.persist(screening8);
            Screening screening9 = new Screening(LocalDateTime.of(2021,11,12,17,10,0),LocalDateTime.of(2021,11,12,19,10,0));
            screening9.setMovie(movie2);
            screening9.setTheater(theater2);
            em.persist(screening9);
            tx.commit();

            //좌석
            for(int i=1;i<=2;i++){
                for(int j=1;j<=5;j++){
                    tx.begin();
                    Sheet sheet1 = new Sheet(j, i,"가능");
                    sheet1.setTheater(theater1);
                    Sheet sheet2 = new Sheet(j, i,"가능");
                    sheet2.setTheater(theater2);
                    em.persist(sheet1);
                    em.persist(sheet2);
                    tx.commit();
                }
            }

            /*
            Screening screening1 = new Screening(movie1, theater1, LocalDateTime.of(2022, 1, 1, 3, 10), LocalDateTime.of(2022, 1, 1, 3, 50));
            em.persist(screening1);
            Ticketing ticketing1 = new Ticketing(user1, screening1);
            em.persist(ticketing1);
            */


        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void cancelTicketing(long ticket_id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // 티켓 find
            Ticketing ticket = em.find(Ticketing.class, ticket_id);

            // state -> '취소'
            ticket.setState("취소");

            // 좌석 Condition -> '가능'으로 변경
            ticket.getScreenSeatList().stream().forEach(screenSeat -> {
                screenSeat.getSheet().setCondition("가능");
            });

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

    }

    public static void num7_data() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // screening(movie, theater): movie(director, actor, participation), user, sheet 생성
        try {
            tx.begin();

// 사용자
            User u1 = new User();
            u1.setName("사용자1");
            u1.setAge(23);
            u1.setAddress(new Address("구미시", "대학로 3길", "zip111"));

            User u2 = new User();
            u2.setName("사용자2");
            u2.setAge(23);
            u2.setAddress(new Address("서울시", "신촌4길", "zip222"));

            em.persist(u1);
            em.persist(u2);

            // 영화
            Movie m1 = new Movie();
            m1.setTitle("유체이탈자");
            m1.setGenre(Genre.Action);
            m1.setOpenDate(LocalDate.of(2021, 11, 24));
            m1.setTime(108);


            Movie m2 = new Movie();
            m2.setTitle("연애 빠진 로맨스");
            m2.setGenre(Genre.Fantasy);
            m2.setOpenDate(LocalDate.of(2021, 10, 12));
            m2.setTime(95);


            Movie m3 = new Movie();
            m3.setTitle("이터널스");
            m3.setGenre(Genre.Action);
            m3.setOpenDate(LocalDate.of(2021, 11, 03));
            m3.setTime(155);


            Movie m4 = new Movie();
            m4.setTitle("애나벨");
            m4.setGenre(Genre.Adventure);
            m4.setOpenDate(LocalDate.of(2019, 6, 26));
            m4.setTime(106);
;

            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);

            // 감독
            Director d1 = new Director("이재준", LocalDate.of(1998, 5, 4), "한국");
            Director d2 = new Director("엄소정", LocalDate.of(2000, 6, 3), "한국");
            Director d3 = new Director("황영민", LocalDate.of(2000, 7, 2), "한국");
            Director d4 = new Director("조우성", LocalDate.of(1998, 8, 1), "한국");

            em.persist(d1);
            em.persist(d2);
            em.persist(d3);
            em.persist(d4);

            // 영화 유체이탈자 - 배우
            Actor actor1 = new Actor("최범휘", LocalDate.of(1997, 9, 30), 173, "angel_bum");
            em.persist(actor1);
            Actor actor2 = new Actor("김현수", LocalDate.of(2000, 3, 7), 165, "kimchi_mine");
            em.persist(actor2);
            Actor actor3 = new Actor("엄소정", LocalDate.of(2000, 9, 7), 164, "cute444");
            em.persist(actor3);
            Actor actor4 = new Actor("이재준", LocalDate.of(1998, 3, 13), 175, "now_juni");
            em.persist(actor4);
            Actor actor5 = new Actor("한현택", LocalDate.of(1998, 5, 6), 186, "yogurt_boy");
            em.persist(actor5);
            Actor actor6 = new Actor("최연웅", LocalDate.of(1998, 8, 20), 175, "yes_yes");


            // 상영관
            Theater th1 = new Theater();
            th1.setFloor(1);
            th1.setName("1상영관");

            Theater th2 = new Theater();
            th2.setFloor(2);
            th2.setName("2상영관");

            em.persist(th1);
            em.persist(th2);

            // 상영 스케줄 , 좌석
            Screening s1 = new Screening();
            s1.setTheater(th1);
            s1.setMovie(m1);
            s1.setStart(LocalDateTime.of(2021, 12, 10, 13, 0, 0));
            s1.setEnd(s1.getStart().plusMinutes(m1.getTime()));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    Sheet sheet = new Sheet();
                    sheet.setRow(i);
                    sheet.setColumn(j);
                    sheet.setCondition("가능");
                    sheet.setTheater(th1);
                    sheet.setScreening(s1);
                    em.persist(sheet);
                }
            }

            Screening s2 = new Screening();
            s2.setTheater(th1);
            s2.setMovie(m1);
            s2.setStart(LocalDateTime.of(2021, 12, 10, 16, 0, 0));
            s2.setEnd(s2.getStart().plusMinutes(m1.getTime()));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    Sheet sheet = new Sheet();
                    sheet.setRow(i);
                    sheet.setColumn(j);
                    sheet.setCondition("가능");
                    sheet.setTheater(th1);
                    sheet.setScreening(s2);
                    em.persist(sheet);
                }
            }

            Screening s3 = new Screening();
            s3.setTheater(th1);
            s3.setMovie(m1);
            s3.setStart(LocalDateTime.of(2021, 12, 10, 19, 0, 0));
            s3.setEnd(s3.getStart().plusMinutes(m1.getTime()));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    Sheet sheet = new Sheet();
                    sheet.setRow(i);
                    sheet.setColumn(j);
                    sheet.setCondition("가능");
                    sheet.setTheater(th1);
                    sheet.setScreening(s3);
                    em.persist(sheet);
                }
            }

            Screening s4 = new Screening();
            s4.setTheater(th2);
            s4.setMovie(m3);
            s4.setStart(LocalDateTime.of(2021, 12, 10, 14, 0, 0));
            s4.setEnd(s4.getStart().plusMinutes(m3.getTime()));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    Sheet sheet = new Sheet();
                    sheet.setRow(i);
                    sheet.setColumn(j);
                    sheet.setCondition("가능");
                    sheet.setTheater(th2);
                    sheet.setScreening(s4);
                    em.persist(sheet);
                }
            }

            Screening s5 = new Screening();
            s5.setTheater(th2);
            s5.setMovie(m3);
            s5.setStart(LocalDateTime.of(2021, 12, 10, 17, 0, 0));
            s5.setEnd(s5.getStart().plusMinutes(m3.getTime()));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    Sheet sheet = new Sheet();
                    sheet.setRow(i);
                    sheet.setColumn(j);
                    sheet.setCondition("가능");
                    sheet.setTheater(th2);
                    sheet.setScreening(s5);
                    em.persist(sheet);
                }
            }

            Screening s6 = new Screening();
            s6.setTheater(th2);
            s6.setMovie(m3);
            s6.setStart(LocalDateTime.of(2021, 12, 10, 20, 0, 0));
            s6.setEnd(s6.getStart().plusMinutes(m3.getTime()));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    Sheet sheet = new Sheet();
                    sheet.setRow(i);
                    sheet.setColumn(j);
                    sheet.setCondition("가능");
                    sheet.setTheater(th2);
                    sheet.setScreening(s6);
                    em.persist(sheet);
                }
            }

            em.persist(s1);
            em.persist(s2);
            em.persist(s3);
            em.persist(s4);
            em.persist(s5);
            em.persist(s6);


            // 예매
            Ticketing t1 = new Ticketing();
            t1.setUser(u1);
            t1.setScreening(s1);
            t1.setState("예매");

            Ticketing t2 = new Ticketing();
            t2.setUser(u2);
            t2.setScreening(s1);
            t2.setState("예매");

            em.persist(t1);
            em.persist(t2);

            // 예매 자리 매팽
            List<Sheet> result1 = em.createQuery("SELECT s FROM Sheet s WHERE s.condition='불가능'", Sheet.class).setFirstResult(0).setMaxResults(2).getResultList();
            for (Sheet sheet : result1) {
                ScreenSeat ss1 = new ScreenSeat();
                ss1.setTicketing(t1);
                sheet.setCondition("불가능");
                ss1.setSheet(sheet);
                em.persist(ss1);
            }

            List<Sheet> result2 = em.createQuery("SELECT s FROM Sheet s WHERE s.condition='불가능'", Sheet.class).setFirstResult(0).setMaxResults(2).getResultList();
            for (Sheet sheet : result2) {
                ScreenSeat ss1 = new ScreenSeat();
                ss1.setTicketing(t2);
                sheet.setCondition("불가능");
                ss1.setSheet(sheet);
                em.persist(ss1);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    

    public static void num7() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();


            ticketing(1, 1, 2, 3);
            ticketing(1, 1, 2, 4);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public static void showTicketingList(long ticket_id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QTicketing qTicketing = new QTicketing("t");
            QScreening qScreening = new QScreening("s");
            QScreenSeat qScreenSeat = new QScreenSeat("ss");
            QSheet qSheet = new QSheet("sh");
            QTheater qTheater = new QTheater("th");
            QMovie qMovie = new QMovie("m");

            Ticketing ticketing = query.selectDistinct(qTicketing).from(qTicketing)
                    .join(qTicketing.screening, qScreening).fetchJoin()
                    .join(qTicketing.screenSeatList, qScreenSeat).fetchJoin()
                    .join(qScreening.movie, qMovie).fetchJoin()
                    .join(qScreening.theater, qTheater).fetchJoin()
                    .join(qScreenSeat.sheet, qSheet).fetchJoin()
                    .where(qTicketing.id.eq(ticket_id))
                    .fetchOne();


            System.out.println("############################################################");
            System.out.println("예매 내역: ");

                System.out.println("영화 이름 : " + ticketing.getScreening().getMovie().getTitle());
                System.out.println("시작 시간 : " + ticketing.getScreening().getStart());
                System.out.println("상영관 : " + ticketing.getScreening().getTheater().getName());
                System.out.println("좌석 : ");
                System.out.println("Row, Column");

                ticketing.getScreenSeatList().stream().forEach(t->
                       System.out.println( t.getSheet().getColumn() + "열"
                        + t.getSheet().getRow()+ "열"));

            System.out.println("############################################################");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void ticketing(long user_id, long screening_id, int row, int col) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // screening(movie, theater): user, ScreenSeat 생성
            Screening screening1 = em.find(Screening.class, screening_id);
            User user = em.find(User.class, user_id);

            screening1.getSheetList().stream().forEach(sheet -> {
                // 예매를 원하는 자리의 Status -> 예매(false)로
                if (sheet.getRow() == row && sheet.getColumn() == col && (sheet.getCondition() == "가능")) {
                    sheet.setCondition("불가능");

                    Ticketing ticketing = new Ticketing();
                    ticketing.setState("예매");
                    ticketing.setUser(user);
                    ticketing.setScreening(screening1);
                    em.persist(ticketing);

                    ScreenSeat ss1 = new ScreenSeat();
                    ss1.setTicketing(ticketing);
                    ss1.setSheet(sheet);
                    em.persist(ss1);

                    em.flush();
                }

            });
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public static void findScreen() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            List<Object[]> resultList = em.createQuery("SELECT sc.id, m.title, sc.start, sc.end, s.row, s.column, s.condition " +
                    "FROM Movie m, Screening sc, Sheet s " +
                    "WHERE m.id=sc.movie.id " +
                    "AND sc.id=s.screening.id " +
                    "order by sc.id").getResultList();

            long screen_id = -1;
            int total_seat = 10;

            // Print
            for (Object[] movie : resultList) {
                long s_id = (long) movie[0];
                String name = (String) movie[1];
                LocalDateTime start = (LocalDateTime) movie[2];
                LocalDateTime end = (LocalDateTime) movie[3];
                Integer row = (Integer) movie[4];
                Integer col = (Integer) movie[5];
                String condition = (String) movie[6];

                if (screen_id != s_id) {
                    screen_id = s_id;
                    System.out.println("\n영화 제목 : " + name);
                    System.out.println("시작 시간 : " + start);
                    System.out.println("종료 시간 : " + end);
                    System.out.println("총 좌석 : " + total_seat);
                    System.out.println("가능한 좌석 : ");
                    System.out.println("Row, Column");
                    System.out.println(row + ", " + col +  "예매 " + condition );
                } else {
                    System.out.println(row + ", " + col +  "예매 " + condition );
                }
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public static void num10() {

    }

    public static void num9() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }


    public static void showScreeningInfo() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        JPAQueryFactory query = new JPAQueryFactory(em);

        QMovie qMovie = new QMovie("m");
        QScreening qScreening = new QScreening("s");

        List<Movie> movies = query.selectFrom(qMovie)
                .join(qMovie, qScreening.movie)
                .fetchJoin()
                .where(qMovie.eq(qScreening.movie))
                .fetch();

        movies.forEach(m -> System.out.println("영화제목: " + m.getTitle())
        );

        try {
            tx.begin();

            System.out.println("###########################################################");
            String query1 = "select s from Screening s";
            //String query2 = "select s from Seats s inner join SCREEN_SEAT t where t.ticket.ticketId in :ticketArray";
            //String query3 = "select t from Tickets t where t.screen =:screen ";
            List<Screening> screeningList = em.createQuery(query1, Screening.class)
                    .getResultList();
            //List<Sheet> sheetList = em.createQuery(query2, Sheet.class)
            //        .getResultList();
            //List<Ticketing> ticketingList = em.createQuery(query3, Ticketing.class)
            //        .getResultList();

            /*
            //Iterator it = screeningList.iterator();
            for(int i=0;i<screeningList.size();i++) {
                System.out.println("영화이름 : " + screeningList.get(i).getMovie().getTitle() + "\t시작시간 : " + screeningList.get(i).getStart() + "\t종료시간 : " + screeningList.get(i).getEnd() + "\t총좌석 : " + 10);
                System.out.println("가능 좌석 수");
                for (Sheet sheet:screeningList.get(i).getTheater().getSeatsList()) {
                    if(sheet.getCondition() == "불가능")
                        System.out.println(seats.getColum() +"-"+ seats.getRoow() + " :  x");
                    else
                        System.out.println(seats.getColum() +"-"+ seats.getRoow() + " :  o");
                }
            }*/
            System.out.println("###########################################################");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public static void num5() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Director director = new Director("토니", LocalDate.of(2020, 3, 20), "미국");
            Movie movie = new Movie("아이언맨", LocalDate.of(2020, 3, 20), Genre.Action, 40, director);
            Movie movie1 = new Movie("아이언맨2", LocalDate.of(2010, 3, 21), Genre.Action, 20, director);
            Movie movie2 = new Movie("아이언맨3", LocalDate.of(2010, 3, 21), Genre.Action, 20, director);
            Movie movie3 = new Movie("아이언맨4", LocalDate.of(2010, 3, 21), Genre.Action, 20, director);
            Movie movie4 = new Movie("아이언맨5", LocalDate.of(2010, 3, 21), Genre.Action, 20, director);
            Movie movie5 = new Movie("아이언맨6", LocalDate.of(2010, 3, 21), Genre.Action, 20, director);


            em.persist(movie);
            em.persist(movie1);
            em.persist(movie2);
            em.persist(movie3);
            em.persist(movie4);
            em.persist(movie5);

            em.persist(director);

            tx.commit();

            QMovie qMovie = new QMovie("m");
            JPAQueryFactory query = new JPAQueryFactory(em);
            List<Movie> movies = query.selectFrom(qMovie)
                    .offset(0)
                    .limit(2)
                    .fetch();

            for (Movie m : movies) {
                System.out.println(m.getInfo());
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void num4() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Director director = null;
        Actor actor = null;
        try {
            tx.begin();
            director = new Director("토니", LocalDate.of(2020, 3, 20), "미국");
            Movie movie = new Movie("아이언맨", LocalDate.of(2020, 3, 20), Genre.Action, 40, director);
            Movie movie1 = new Movie("아이언맨2", LocalDate.of(2010, 3, 21), Genre.Action, 20, director);
            actor = new Actor("배우", LocalDate.of(2020, 3, 10), 180, "@멋진배우");
            Participation attempt = new Participation(movie, actor, "주연");

            em.persist(movie);
            em.persist(movie1);
            em.persist(actor);
            em.persist(director);
            em.persist(attempt);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        findMovieWithWorkerOpeningDateRunningTime(director, actor, LocalDate.of(2020, 3, 20));
    }

    public static void findMovieWithWorkerOpeningDateRunningTime(Director director, Actor actor, LocalDate openingDate) {
        EntityManager em = emf.createEntityManager();
        JPAQueryFactory query = new JPAQueryFactory(em);

        QMovie qMovie = new QMovie("m");
        QParticipation qParticipation = new QParticipation("q");

        List<Movie> movies = query.selectFrom(qMovie)
                .join(qMovie.actors, qParticipation)
                .fetchJoin()
                .where(qMovie.director.eq(director)
                        .or(qParticipation.actor.eq(actor))
                        .or(qMovie.openDate.eq(openingDate)))
                .fetch();

        movies.forEach(m -> System.out.println(m.getInfo())
        );
    }

    public static void showMovieWithWorkerId(Long movieId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Movie findMovie = em.find(Movie.class, movieId);
            System.out.println(findMovie.getInfo());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void createMovieInfo() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Director director = new Director("토니", LocalDate.of(2020, 3, 20), "미국");
            Movie movie = new Movie("아이언맨", LocalDate.of(2020, 3, 20), Genre.Action, 40, director);
            Movie movie1 = new Movie("아이언맨2", LocalDate.of(2010, 3, 21), Genre.Action, 20, director);
            Actor actor = new Actor("배우", LocalDate.of(2020, 3, 10), 180, "@멋진배우");
            Participation attempt = new Participation(movie, actor, "주연");

            em.persist(movie);
            em.persist(movie1);
            em.persist(actor);
            em.persist(director);
            em.persist(attempt);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void createUser(String name, Integer age, String city, String street, String zipCode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            User user = new User(name, age, city, street, zipCode);
            em.persist(user);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void setUser() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            User user = new User("이준재", 22, "원주시", "남산로", "22932");
            em.persist(user);
            user.setAddress(new Address("구미시", "거의동", "21312"));
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
