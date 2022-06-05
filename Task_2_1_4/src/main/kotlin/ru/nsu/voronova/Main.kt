package ru.nsu.voronova

import ru.nsu.voronova.builder.course

fun main(args: Array<String>) {
    val course = course {
        tasks {
            task {
                id = 1
                name = "Snake Game"
                points = 5
                deadline = "10/04/2022"
            }
            task {
                id = 2
                name = "Pizzeria"
                points = 4
                deadline = "08/03/2022"
            }
        }
        groups {
            group {
                name = "20214"
                students {
                    student {
                        nickname = "PeachMood"
                        name = "Ann"
                        surname = "Voronova"
                        repository = "https://github.com/PeachMood/OOP"
                        givenTasks("Snake Game", "Pizzeria")
                    }
                    student {
                        nickname = "dTolmachev1"
                        name = "Denis"
                        surname = "Tolmachev"
                        repository = "https://github.com/dTolmachev1/OOP"
                        givenTasks(2)
                    }
                }
                lessons {
                    lesson {
                        date = "01/06/2022"
                        attendance("Voronova", "Tolmachev")
                    }
                    lesson {
                        date = "27/05/2022"
                        attendance("Tolmachev")
                    }
                }
            }
        }
        checkpoints {
            checkpoint("Контрольна неделя 1", "06/03/2022")
            checkpoint("Контрольная неделя 2", "24/04/2022")
        }
    }
}