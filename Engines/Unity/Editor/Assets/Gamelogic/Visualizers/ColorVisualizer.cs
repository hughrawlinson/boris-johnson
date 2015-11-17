﻿using Improbable.Color;
using Improbable.Math;
using Improbable.Unity.Visualizer;
using UnityEngine;

namespace Assets.Gamelogic.Visualizers
{
    public class ColorVisualizer : MonoBehaviour
    {
        [Require] public ColorStateReader Color;

        public Renderer[] Renderers;

        private void OnEnable()
        {
            Color.ValueUpdated += HandleValueUpdated;
        }

        private void OnDisable()
        {
            Color.ValueUpdated -= HandleValueUpdated;
        }

        private void HandleValueUpdated(Vector3f color)
        {
            if (Renderers != null)
            {
                var unityColor = new Color(color.x, color.y, color.z);
                foreach (var renderer in Renderers)
                {
                    renderer.material.color = unityColor;
                }
            }
        }
    }
}