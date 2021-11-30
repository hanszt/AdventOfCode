package hzt.aoc.day20

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TileTest {

    private val expected = """
            #### 
            #   #
            #### 
            #    
            #    
            
            #####
              # #
              # #
              # #
               # 
            
                #
                #
             ####
            #   #
             ####
            
             #   
            # #  
            # #  
            # #  
            #####
            
             ####
            #   #
             ####
                #
                #
            
               # 
              # #
              # #
              # #
            #####
            
            #    
            #    
            #### 
            #   #
            #### 
            
            #####
            # #  
            # #  
            # #  
             #    
        """.trimIndent().trim()

    @Test
    fun testOrientationsWithP() {
        val pattern = listOf(
            "#### ",
            "#   #",
            "#### ",
            "#    ",
            "#    "
        )
        val orientations = Tile(pattern).orientationsAsString().trimIndent().trim()
        println(orientations)
        assertEquals(expected, orientations)
    }
}
