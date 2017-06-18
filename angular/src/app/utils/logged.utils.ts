/**
 * Created by Milos on 24-May-17.
 */

export class LoggedUtils
{
  static getId()
  {
    return JSON.parse(localStorage.getItem("loggedUser")).id;
  }

  static getToken()
  {
    return JSON.parse(localStorage.getItem("loggedUser")).token;
  }

  static hasRole(role: string)
  {
    let roles = JSON.parse(localStorage.getItem("loggedUser")).role;
    return roles.indexOf(role) != -1;
  }

  static getEmail()
  {
    return JSON.parse(localStorage.getItem("loggedUser")).email;
  }

  static clearLocalStorage()
  {
    localStorage.clear();
  }

  static isEmpty()
  {
    return localStorage.getItem("loggedUser") === null;
  }

  static isLogged()
  {
    
  }
}
