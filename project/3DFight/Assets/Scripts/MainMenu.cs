using UnityEngine;
using System.Collections;

public class MainMenu : MonoBehaviour {

	public void StartMenu()
    {
        Application.LoadLevel(1);
    }

    public void ExitMenu()
    {
        Application.Quit();
    }
}
