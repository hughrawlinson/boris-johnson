package improbable.natures

import improbable.behaviours.player.controls.{DelegateLocalPlayerCheckToOwnerBehaviour, DelegatePlayerControlsToOwnerBehaviour}
import improbable.behaviours.player.controls.physical.PlayerBehaviour
import improbable.corelib.entity.nature.definitions._
import improbable.corelib.entity.nature.{NatureApplication, NatureDescription}
import improbable.corelib.util.EntityOwner
import improbable.math.{Coordinates, Vector3d}
import improbable.papi.engine.EngineId
import improbable.papi.entity.behaviour.EntityBehaviourDescriptor
import improbable.player.LocalPlayerCheckState
import improbable.player.controls.PlayerControlsState
import improbable.player.physical.PlayerState
import improbable.util.EntityPrefabs._

object PlayerNature extends NatureDescription {

  override val dependencies = Set[NatureDescription](BotEntity)

  override def activeBehaviours: Set[EntityBehaviourDescriptor] = {
    Set(
      descriptorOf[PlayerBehaviour],
      descriptorOf[DelegatePlayerControlsToOwnerBehaviour],
      descriptorOf[DelegateLocalPlayerCheckToOwnerBehaviour]
    )
  }

  def apply(engineId: EngineId): NatureApplication = {
    application(
      states = Seq(
        EntityOwner(ownerId = Some(engineId)),
        PlayerState(forceMagnitude = 20.0f),
        PlayerControlsState(movementDirection = Vector3d.zero),
        LocalPlayerCheckState()
      ),
      natures = Seq(
        BotEntity(prefab = PLAYER, position = Coordinates(0, 0.5, 0))
      )
    )
  }

}
