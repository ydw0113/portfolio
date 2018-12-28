using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class L3 : MonoBehaviour {

	// Use this for initialization
	void Start () {

	}

	// Update is called once per frame
	void OnMouseDown(){
		SceneManager.LoadScene ("basic3");
	}
}
