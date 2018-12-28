using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class L2 : MonoBehaviour {
	GameObject a;


	// Use this for initialization
	void Start () {

	}

	// Update is called once per frame

	void OnMouseDown(){
		SceneManager.LoadScene ("basic2");
	}
}
