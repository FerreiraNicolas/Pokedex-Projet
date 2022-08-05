package scala.fplesson3

import monix.eval.Task
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

object Http {
  
  def request(url: String): Task[String] = {
    Task {      
      val conn = new URL(url).openConnection()

      conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36")
      conn.connect()
      conn.getInputStream()
    }.bracket {
      inputStream => Task {
        val reader = new BufferedReader(new InputStreamReader(inputStream))

        reader.lines.reduce("", (acc, line) => acc + line)
      }
    } {
      inputStream => Task(inputStream.close())
    }
  }
}
