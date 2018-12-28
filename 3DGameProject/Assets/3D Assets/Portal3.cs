using UnityEngine;
using System.Collections;

public class Portal3 : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	void OnTriggerEnter(Collider other){

		if(other.gameObject.tag=="Player"){
			GameObject.FindWithTag ("Player").transform.position = new Vector3(47.0f, 1.0f, 42.0f);

		}

	}
}
