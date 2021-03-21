# VM.Lab1

Лабораторная работа 1. «Решение системы линейных алгебраических уравнений СЛАУ»

Метод простых итераций


[![GitHub stars][stars-shield]][stars-url]
[![GitHub issues][issues-shield]][issues-url]
[![GitHub][license-shield]][license-url]
![GitHub repo size](https://img.shields.io/github/repo-size/KirillShakhov/VM.Lab1)
![GitHub last commit](https://img.shields.io/github/last-commit/KirillShakhov/VM.Lab1)

* В программе численный метод должен быть реализован в виде отдельной подпрограммы или класса, в который исходные данные передаются в качестве параметров, выходные - тоже (либо возвращаемое значение). 
* Размерность матрицы n<=20 (задается из файла или с клавиатуры - по выбору конечного пользователя).
* Должна быть реализована возможность ввода коэффициентов матрицы,  как с клавиатуры, так и из файла (по выбору конечного пользователя).

Обязательно: Тестовые данные на матрице большого размера (5x5 / 6x6...) + в отчёт с решением.

Для итерационных методов должно быть реализовано:
* Точность задается с клавиатуры/файла
* Проверка диагонального преобладания (в случае, если диагональное преобладание в исходной  матрице отсутствует, сделать перестановку строк/столбцов до тех пор, пока преобладание не будет достигнуто). В случае невозможности достижения диагонального преобладания - выводить соответствующее сообщение.
* Вывод вектора неизвестных: 
* Вывод количества итераций, за которое было найдено решение
* Вывод вектора погрешностей:

[stars-shield]: https://img.shields.io/github/stars/KirillShakhov/VM.Lab1?style=social
[stars-url]: https://github.com/KirillShakhov/VM.Lab1/stargazers
[issues-shield]: https://img.shields.io/github/issues/KirillShakhov/VM.Lab1
[issues-url]: https://github.com/KirillShakhov/VM.Lab1/issues
[license-shield]: https://img.shields.io/github/license/KirillShakhov/VM.Lab1
[license-url]: https://github.com/KirillShakhov/VM.Lab1/blob/master/LICENSE
