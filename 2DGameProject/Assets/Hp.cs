using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
public class Hp : MonoBehaviour {
	GameObject HP;
	float c=0;
	Scene s;
	// Use this for initialization
	void Start () {
		this.HP = GameObject.Find ("HP");
		s = SceneManager.GetActiveScene();
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	public void DecreaseHp(){
		this.HP.GetComponent<Image> ().fillAmount -= 0.2f;
		c++;
		if (c == 5) {

			SceneManager.LoadScene (s.name);
		}
	}
}
