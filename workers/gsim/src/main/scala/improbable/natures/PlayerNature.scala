package improbable.natures

import improbable.behaviours.physical.ExtinguishFlamesBehaviour
import improbable.behaviours.player.controls.physical.PlayerBehaviour
import improbable.behaviours.player.controls.{DelegateLocalPlayerCheckToOwnerBehaviour, DelegatePlayerControlsToOwnerBehaviour, RaycastRequestorBehaviour, RaycastResponderBehaviour}
import improbable.corelib.natures.{BaseNature, NatureApplication, NatureDescription}
import improbable.corelib.util.EntityOwner
import improbable.corelibrary.rigidbody.RigidbodyNature
import improbable.corelibrary.transforms.TransformNature
import improbable.entity.physical.FreezeConstraints
import improbable.math.Vector3d
import improbable.papi.engine.EngineId
import improbable.papi.entity.behaviour.EntityBehaviourDescriptor
import improbable.physical.{RaycastRequest, RaycastResponse}
import improbable.player.LocalPlayerCheckState
import improbable.player.controls.PlayerControlsState
import improbable.player.physical.PlayerState
import improbable.util.EntityPrefabs._

object PlayerNature extends NatureDescription {

  override def dependencies: Set[NatureDescription] = Set(
    BaseNature,
    TransformNature,
    RigidbodyNature
  )

  override def activeBehaviours: Set[EntityBehaviourDescriptor] = {
    Set(
      descriptorOf[PlayerBehaviour],
      descriptorOf[DelegatePlayerControlsToOwnerBehaviour],
      descriptorOf[DelegateLocalPlayerCheckToOwnerBehaviour],
      descriptorOf[RaycastRequestorBehaviour],
      descriptorOf[RaycastResponderBehaviour],
      descriptorOf[ExtinguishFlamesBehaviour]
    )
  }

  def apply(engineId: EngineId): NatureApplication = {
    application(
      states = Seq(
        EntityOwner(ownerId = Some(engineId)),
        PlayerState(forceMagnitude = 20.0f),
        PlayerControlsState(movementDirection = Vector3d.zero),
        LocalPlayerCheckState(),
        RaycastRequest(),
        RaycastResponse()
      ),
      natures = Seq(
        BaseNature(entityPrefab = PLAYER),
        TransformNature(globalPosition = Vector3d(0, 1, 0)),
        RigidbodyNature(rotationConstraints = FreezeConstraints(x = true, y = true, z = true))
      )
    )
  }

}
