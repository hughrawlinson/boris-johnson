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
    world.timing.after(500.milliseconds) {
      if (fire.temperature < 132) {
        fire.update.temperature(fire.temperature + 1).finishAndSend()
        updateTempAndScheduleNewUpdate
      }
    }
  }
}