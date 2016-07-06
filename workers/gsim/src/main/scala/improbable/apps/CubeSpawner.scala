package improbable.apps

import improbable.math.Vector3d
import improbable.natures.BotNature
import improbable.papi.world.AppWorld
import improbable.papi.worldapp.WorldApp
import improbable.physical.Fire

import scala.concurrent.duration._
import scala.language.postfixOps

class CubeSpawner(appWorld: AppWorld) extends WorldApp {

  spawnCubes()

  private def spawnCubes(): Unit = {
    appWorld.entities.spawnEntity(BotNature(Vector3d(5.0, 1.0, 5.0), onFire = true))

    Range.inclusive(1, 50).foreach {
      i =>
        appWorld.timing.after((200 * i) millis) {
          appWorld.entities.spawnEntity(BotNature(Vector3d.unitY * 20.0f + Vector3d.unitX * 10.0f))
        }
    }
  }
}
