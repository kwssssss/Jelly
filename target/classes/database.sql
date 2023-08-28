SELECT * FROM TRAVEL;

ALTER TABLE TRAVEL
ADD CONSTRAINT TRAVEL_PK PRIMARY KEY(NO);

-- 1. 페이지 단위로 10개씩 보여주기
-- 정렬 기준: REGION, TITLE

-- 전체 데이터 건수
SELECT COUNT(*) TOTAL_COUNT
FROM TRAVEL;

-- 한 페이지 당 10개 목록으로 운영
-- 예] 2페이지의 목록 얻기 NO 11~20, 순번 얻을때 쓰는거 = ROWNUM
-- 정렬 : REGION, TITLE
SELECT *
FROM(
SELECT ROW_NUMBER() OVER(ORDER BY REGION, TITLE) SEQ, T.*
FROM TRAVEL T
)
WHERE SEQ BETWEEN 11 AND 20; -- BETWEEN ?? AND ?? :? 부분이 나중에 변수로 바뀜 

CREATE OR REPLACE VIEW TRAVEL_VIEW
AS 
	SELECT *
	FROM(
		SELECT ROW_NUMBER() OVER(ORDER BY REGION, TITLE) SEQ, T.*
		FROM TRAVEL T
	);

SELECT * FROM TRAVEL_VIEW
WHERE SEQ BETWEEN 11 AND 20; --목록보기할때 사용할 쿼리



-- 2. 지역별로 보여주기
-- 2-1) 지역을 제시하고 선택 후 해당 지역 목록 출력

-- 2-1) 지역목록 얻기
SELECT DISTINCT REGION FROM TRAVEL; --DISTINCT 중복 제거

-- 권역별 관광지 개수 , GROUP BY절?
-- 수도권(13)
-- 전라권(7)
SELECT REGION, COUNT(*) COUNT
FROM TRAVEL
GROUP BY REGION
ORDER BY COUNT(*);  --권역별 제시 후 목록 보기

-- 2-2) 선택지역의 목록 얻기
SELECT * FROM TRAVEL t 
WHERE REGION = '수도권'
ORDER BY TITLE;

-- 3. 검색:
-- 검색 대상 컬럼: TITLE, DESCRIPTION
SELECT * FROM TRAVEL WHERE TITLE LIKE '%해수욕장%' ORDER BY REGION, TITLE;

--SELECT * FROM TRAVEL
--WHERE DESCRIPTION LIKE '%해수욕장%'; --DESCRIPTION이 지금 당장은 숫자로 읽혀서 적용이 안됨


-- 4. 상세보기
SELECT * FROM TRAVEL WHERE NO = 10;