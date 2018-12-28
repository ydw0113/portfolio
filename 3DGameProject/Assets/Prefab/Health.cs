using UnityEngine;
using System.Collections;

public class Health : MonoBehaviour {
	GameObject Player;
	GameObject Healthprefab;
	// Use this for initialization
	void Start () {
		this.Player = GameObject.FindWithTag ("Player");
		this.Healthprefab = GameObject.FindWithTag ("Health");
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	void OnTriggerEnter(Collider other){

		if(other.gameObject.tag=="Player"){
			GameObject director = GameObject.FindWithTag("Player");
			director.GetComponent<CharacterStatus> ().HP  +=30;
			GameObject director2 = GameObject.Find("GameDirector");
			director2.GetComponent<HP>().creaseHp();
			Destroy (Healthprefab);
		}

	}
}
