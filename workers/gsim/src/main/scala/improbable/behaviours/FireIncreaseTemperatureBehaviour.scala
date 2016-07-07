package improbable.behaviours.physical

import improbable.papi.entity.{Entity, EntityBehaviour}
import improbable.papi.world.World
import improbable.physical.{Fire, FireWriter}

import scala.concurrent.duration._

class FireIncreaseTemperatureBehaviour(fire: FireWriter, world: World, entity: Entity) extends EntityBehaviour {
  entity.watch[Fire].bind.onFire {
    (isOnFire) => {
      if (isOnFire) {
        updateTempAndScheduleNewUpdate
      }
    }
  }
  def updateTempAndScheduleNewUpdate: Unit = {
    if (fire.onFire && fire.temperature < 132) {
      world.timing.after(500.milliseconds) {
        fire.update.temperature(fire.temperature + 1).finishAndSend()
        updateTempAndScheduleNewUpdate
      }
    }
  }
}