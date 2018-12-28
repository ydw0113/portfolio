using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class GameDirector : MonoBehaviour {
	GameObject hpGage;
	//Scene Scene;
	float c=0;
	Scene s;
	// Use this for initialization
	void Start () {
		this.hpGage = GameObject.Find ("hpGage");
		s = SceneManager.GetActiveScene();
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	public void DecreaseHp(){
		this.hpGage.GetComponent<Image> ().fillAmount -= 0.2f;
		c++;
		if (c == 5) {

			SceneManager.LoadScene (s.name);
		}
	}
}
