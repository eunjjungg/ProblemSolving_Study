상위 n개 레코드
```
SELECT NAME 
FROM (SELECT NAME FROM ANIMAL_INS
ORDER BY DATETIME ASC)
WHERE rownum = 1
```

- 오라클에서 개수제한 걸 때 `where rownum = n` 사용해야 함. 