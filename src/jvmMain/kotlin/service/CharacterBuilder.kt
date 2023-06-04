package service

import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Paths

class CharacterBuilder {

    fun buildCharacters(){
        val gameCharacters = Files.readAllLines(Paths.get("GameCharacters.txt"))

    }
}