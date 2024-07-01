insert into occupations (created_date, last_modified_date, name)
values
    (now(), now(),'개발'),
    (now(), now(), '경영·비즈니스'),
    (now(), now(), '마케팅·광고·홍보'),
    (now(), now(), '디자인'),
    (now(), now(), '영업'),
    (now(), now(), '고객서비스·리테일'),
    (now(), now(), '미디어'),
    (now(), now(), '엔지니어링·설계'),
    (now(), now(), 'HR'),
    (now(), now(), '게임 제작'),
    (now(), now(), '제조·생산'),
    (now(), now(), '금융'),
    (now(), now(), '교육'),
    (now(), now(), '사무·회계')
;

insert into topics (created_date, last_modified_date, content)
values
    (now(), now(),'건강/운동'),
    (now(), now(), '학업'),
    (now(), now(), '취미/여가'),
    (now(), now(), '대인관계'),
    (now(), now(), '경험/도전'),
    (now(), now(), '사랑/가족'),
    (now(), now(), '자기개발'),
    (now(), now(), '커리어/자기계발')
;

INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (1, now(), now(), '이직하기', '이직 목표 회사 정하기, 경력기술서/포트폴리오 정리하기, 이직 지원하기, 면접보기, 최종 합격받고 입사일 확정받기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (2, now(), now(), '유럽여행가기', '여행일 정하기, 항공권 예약하기, 여행 일정 계획하기, 출국하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (3, now(), now(), '해외여행가기', '여행일 정하기, 여행갈 국가 정하기, 항공권 예약하기, 여행 일정 계획하기, 출국하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (4, now(), now(), '취업하기', '목표 기업정하기, 지원하기, 면접보기, 최종 합격받고 입사일 확정하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (5, now(), now(), '한라산 등반하기', '한라산 등반 날짜 정하기, 한라산 등반 신고하기, 항공권 예약하기, 등산하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (6, now(), now(), '퇴사하기', '퇴사일 정하기, 퇴사 후 계획 정하기, 퇴사 통보일 정하기, 퇴사 통보하기, 퇴사 완료');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (7, now(), now(), '장학금 받기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (8, now(), now(), '바디프로필 찍기', 'PT 등록하기, 바디 프로필 촬영 예약하기, 바디 프로필 찍기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (9, now(), now(), '올해 책 10권 읽기', '읽을 책 10권 정하기, 1번째 책 완독하기, 2번째 책 완독하기, 3번째 책 완독하기, 4번째 책 완독하기, 5번째 책 완독하기, 6번째 책 완독하기, 7번째 책 완독하기, 8번째 책 완독하기, 9번째 책 완독하기, 10번째 책 완독하기,');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (10, now(), now(), '글램핑가기', '글램핑 예약하기, 글램핑가기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (11, now(), now(), '감사일기 쓰기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (12, now(), now(), '가족사진 찍기', '가족사진 찍을 날짜 정하기, 가족사진 찍을 스튜디오/장소 예약하기, 가족사진 찍기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (13, now(), now(), '머리카락 기부하기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (14, now(), now(), '좋아하는 것 1개 찾기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (15, now(), now(), '싫어하는 것 1개 찾기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (16, now(), now(), '일본어 공부하기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (17, now(), now(), '영어 공부하기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (18, now(), now(), '중국어 공부하기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (19, now(), now(), '자격증 따기', '자격증 정하기, 자격증 시험 신청하기, 자격증 시험 치기, 합격하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (20, now(), now(), '운전면허 따기', '운전면허 학원 등록하기, 필기 시험 합격하기, 기능 시험 합격하기, 주행 시험 합격하기, 운전면허증 발급 완료하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (21, now(), now(), '뮤지컬 보러가기', '보고 싶은 뮤지컬 정하기, 뮤지컬 예매하기, 뮤지컬 보러가기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (22, now(), now(), '콘서트 가보기', '가고 싶은 콘서트 정하기, 콘서트 예매하기, 콘서트가기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (23, now(), now(), '패스티벌 가보기', '가고 싶은 패스티벌 정하기, 패스티벌 예매하기, 패스티벌 가기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (24, now(), now(), '사이드 프로젝트하기', '사이드 프로젝트 주제 정하기, 사이드 프로젝트 팀원 모으기, 사이드 프로젝트 시작하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (25, now(), now(), '취미 1개 만들기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (26, now(), now(), '사진 앨범 만들기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (27, now(), now(), '혼자 인생네컷 찍기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (28, now(), now(), '애인 만들기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (29, now(), now(), '가족 여행가기', '여행 날짜 정하기, 여행 장소 정하기, 숙소 예약하기, 여행 계획 세우기, 여행가기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (30, now(), now(), '해돋이 보러가기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (31, now(), now(), '등산하기', '등산할 산 정하기, 등산 날짜 정하기, 등산하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (32, now(), now(), '동아리 들기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (33, now(), now(), '100만원 모으기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (34, now(), now(), '500만원 모으기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (35, now(), now(), '1000만원 모으기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (36, now(), now(), '새로운 친구 사귀기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (37, now(), now(), '외국인 친구 사귀기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (38, now(), now(), '악기 배우기', '배울 악기 정하기, 어떻게 배울지 정하기, 배우기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (39, now(), now(), '마라톤 나가보기', '참가할 마라톤 정하기, 마라톤 참여 신청하기, 마라톤 완주하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (40, now(), now(), '책 100권 읽기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (41, now(), now(), '봉사활동 하기', '참여할 봉사활동 정하기, 봉사활동할 날짜 정하기, 봉사활동 하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (42, now(), now(), '다이어트 성공하기', '목표 몸무게 정하기, 식단 계획하기, 운동 계획하기, 목표 몸무게 달성 완료');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (43, now(), now(), '부모님께 편지쓰기', '편지지 사기, 편지쓰기, 편지 전달하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (44, now(), now(), '공모전 참가하기', '참가할 공모전 정하기, 공모전 제출하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (45, now(), now(), '기부하기', '기부할 기관 정하기, 기부하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (46, now(), now(), '머리 기르기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (47, now(), now(), '금연하기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (48, now(), now(), '금주하기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (49, now(), now(), '혼자 여행가기', '여행 장소 정하기, 여행 날짜 정하기, 여행 계획 세우기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (50, now(), now(), '패러글라이딩 하기', '패러글라이딩할 날짜 정하기, 패러글라이딩 예약하기, 패러글라이딩 하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (51, now(), now(), '스카이다이빙 해보기', '스카이다이빙할 날짜 정하기, 스카이다이빙 예약하기, 스카이다이빙하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (52, now(), now(), '블로그하기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (53, now(), now(), '유튜브 크리에이터 활동 시작하기', '유튜브 채널 개설하기, 영상 촬영하기, 영상 편집하기, 영상 업로드하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (54, now(), now(), '수영 마스터하기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (55, now(), now(), '클라이밍 해보기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (56, now(), now(), '올해의 증명사진 남기기', '촬영 날짜 정하기, 촬영 스튜디오 예약하기, 증명사진 촬영하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (57, now(), now(), '옛날에 찍은 사진 똑같이 찍기', '따라 찍을 사진 정하기, 비슷한 옷 구매하기, 똑같이 찍기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (58, now(), now(), '부모님께 멋진 식사 대접하기', '메뉴 정하기, 식사 날짜 정하기, 식당 예약하기, 같이 식사하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (59, now(), now(), '일기 쓰기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (60, now(), now(), '호캉스 해보기', '호캉스 날짜 정하기, 호텔 예약하기, 호캉스 하기');
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (61, now(), now(), '비키니 입어보기', null);
INSERT INTO bucket_template (id, created_date, last_modified_date, bucket_name, bucket_todo_names) VALUES (62, now(), now(), '나를 위한 선물하기', '선물 정하기, 구매하기');



INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (1, now(), now(), 1, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (2, now(), now(), 2, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (3, now(), now(), 3, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (4, now(), now(), 4, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (5, now(), now(), 5, 1);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (6, now(), now(), 5, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (7, now(), now(), 6, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (8, now(), now(), 7, 2);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (9, now(), now(), 7, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (10, now(), now(), 8, 1);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (11, now(), now(), 8, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (12, now(), now(), 9, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (13, now(), now(), 10, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (14, now(), now(), 11, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (15, now(), now(), 12, 6);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (16, now(), now(), 13, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (17, now(), now(), 14, 7);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (18, now(), now(), 15, 7);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (19, now(), now(), 16, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (20, now(), now(), 17, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (21, now(), now(), 18, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (22, now(), now(), 19, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (23, now(), now(), 20, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (24, now(), now(), 21, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (25, now(), now(), 22, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (26, now(), now(), 23, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (27, now(), now(), 24, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (28, now(), now(), 25, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (29, now(), now(), 26, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (30, now(), now(), 27, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (31, now(), now(), 28, 6);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (32, now(), now(), 29, 6);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (33, now(), now(), 30, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (34, now(), now(), 31, 1);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (35, now(), now(), 32, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (36, now(), now(), 33, 7);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (37, now(), now(), 34, 7);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (38, now(), now(), 35, 7);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (39, now(), now(), 36, 4);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (40, now(), now(), 37, 4);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (41, now(), now(), 38, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (42, now(), now(), 39, 1);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (43, now(), now(), 39, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (44, now(), now(), 40, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (45, now(), now(), 41, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (46, now(), now(), 42, 1);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (47, now(), now(), 42, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (48, now(), now(), 43, 6);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (49, now(), now(), 44, 8);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (50, now(), now(), 45, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (51, now(), now(), 46, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (52, now(), now(), 47, 1);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (53, now(), now(), 47, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (54, now(), now(), 48, 1);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (55, now(), now(), 48, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (56, now(), now(), 49, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (57, now(), now(), 50, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (58, now(), now(), 51, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (59, now(), now(), 52, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (60, now(), now(), 52, 7);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (61, now(), now(), 53, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (62, now(), now(), 53, 7);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (63, now(), now(), 54, 1);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (64, now(), now(), 54, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (65, now(), now(), 55, 1);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (66, now(), now(), 55, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (67, now(), now(), 56, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (68, now(), now(), 57, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (69, now(), now(), 58, 6);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (70, now(), now(), 59, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (71, now(), now(), 60, 3);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (72, now(), now(), 61, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (73, now(), now(), 62, 5);
INSERT INTO bucket_template_topic (id, created_date, last_modified_date, bucket_template_id, topic_id) VALUES (74, now(), now(), 62, 7);