Create a query to find all movies in the Sci-Fi genre.
mysql> select * from movies where genre = 'Sci-Fi';
+-------------------+---------+--------+------------+--------+
| title             | runTime | genre  | imdb_score | rating |
+-------------------+---------+--------+------------+--------+
| Howard the Duck   |     110 | Sci-Fi |        4.6 | PG     |
| Starship Troopers |     129 | Sci-Fi |        7.2 | PG-13  |
+-------------------+---------+--------+------------+--------+
2 rows in set (0.05 sec)


Create a query to find all films that scored at least a 6.5 on IMDB
mysql> select * from movies where imdb_score > 6.4;
+-------------------+---------+-------------+------------+--------+
| title             | runTime | genre       | imdb_score | rating |
+-------------------+---------+-------------+------------+--------+
| Starship Troopers |     129 | Sci-Fi      |        7.2 | PG-13  |
| Waltz With Bashir |      90 | Documentary |        8.0 | R      |
| Spaceballs        |      96 | Comedy      |        7.1 | PG     |
| Monsters Inc      |      92 | Animation   |        8.0 | G      |
| Honey             |      93 | Romance     |        7.5 | PG-13  |
| Friday            |      90 | Comedy      |        9.2 | R      |
+-------------------+---------+-------------+------------+--------+
6 rows in set (0.01 sec)

For parents who have young kids, but who don't want to sit through long children's movies, create a query to find all of the movies rated G or PG that are less than 100 minutes long.
mysql> select * from movies where rating  = 'PG' or rating ='G' AND runTime < 100;
+-----------------+---------+-----------+------------+--------+
| title           | runTime | genre     | imdb_score | rating |
+-----------------+---------+-----------+------------+--------+
| Howard the Duck |     110 | Sci-Fi    |        4.6 | PG     |
| Spaceballs      |      96 | Comedy    |        7.1 | PG     |
| Monsters Inc    |      92 | Animation |        8.0 | G      |
+-----------------+---------+-----------+------------+--------+
3 rows in set (0.01 sec)

Create a query to show the average runtimes of movies rated below a 7.5, grouped by their respective genres.
mysql> select avg(runTime) from movies where imdb_score < 7.5 group by genre;
+--------------+
| avg(runTime) |
+--------------+
|     119.5000 |
|      83.0000 |
|      96.0000 |
+--------------+
3 rows in set (0.01 sec)


There's been a data entry mistake; Starship Troopers is actually rated R, not PG-13. Create a query that finds the movie by its title and changes its rating to R.
mysql> update movies set rating = 'R' where title = 'Starship Troopers';
Query OK, 1 row affected (0.03 sec)
Rows matched: 1  Changed: 1  Warnings: 0

Show the ID number and rating of all of the Horror and Documentary movies in the database. Do this in only one query.
select id, rating from movies where genre = 'Documentary' or genre = 'Horror';
+----+--------+
| id | rating |
+----+--------+
|  2 | TV-14  |
|  4 | R      |
+----+--------+
2 rows in set (0.00 sec)

This time let's find the average, maximum, and minimum IMDB score for movies of each rating.
select avg(imdb_score), max(imdb_score), min(imdb_score) from movies group by rating;
+-----------------+-----------------+-----------------+
| avg(imdb_score) | max(imdb_score) | min(imdb_score) |
+-----------------+-----------------+-----------------+
|         5.85000 |             7.1 |             4.6 |
|         4.70000 |             4.7 |             4.7 |
|         8.13333 |             9.2 |             7.2 |
|         8.00000 |             8.0 |             8.0 |
|         7.50000 |             7.5 |             7.5 |
+-----------------+-----------------+-----------------+
5 rows in set (0.01 sec)


That last query isn't very informative for ratings that only have 1 entry. use a HAVING COUNT(*) > 1 clause to only show ratings with multiple movies showing.
 select avg(imdb_score), max(imdb_score), min(imdb_score) from movies having count(*) > 1 order by rating;
+-----------------+-----------------+-----------------+
| avg(imdb_score) | max(imdb_score) | min(imdb_score) |
+-----------------+-----------------+-----------------+
|         7.03750 |             9.2 |             4.6 |
+-----------------+-----------------+-----------------+
1 row in set (0.01 sec)

Let's make our movie list more child-friendly. Delete all entries that have a rating of R. Remember to record your query in script.sql.
mysql> delete from movies where rating = 'R';
Query OK, 3 rows affected (0.05 sec)
mysql> select * from movies;
+-----------------+---------+-----------+------------+--------+----+
| title           | runTime | genre     | imdb_score | rating | id |
+-----------------+---------+-----------+------------+--------+----+
| Howard the Duck |     110 | Sci-Fi    |        4.6 | PG     |  1 |
| Lavalantula     |      83 | Horror    |        4.7 | TV-14  |  2 |
| Spaceballs      |      96 | Comedy    |        7.1 | PG     |  5 |
| Monsters Inc    |      92 | Animation |        8.0 | G      |  6 |
| Honey           |      93 | Romance   |        7.5 | PG-13  |  7 |
+-----------------+---------+-----------+------------+--------+----+
5 rows in set (0.00 sec)