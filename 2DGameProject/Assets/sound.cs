﻿using UnityEngine;
using System.Collections;

public class sound : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		GetComponent<AudioSource> ().Play ();
	
	}
}