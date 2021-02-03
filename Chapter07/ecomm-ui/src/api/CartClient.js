import Config from "./Config";

export default class CartClient {
  auth;
  constructor(auth) {
    this.auth = auth;
    this.config = new Config();
  }

  async fetch() {
    if (this.config.tokenExpired()) {
      await this.auth.refreshToken();
    }
    return fetch(this.config.CART_URL + "/" + this.auth.token.userId, {
      method: "GET",
      mode: "cors",
      headers: {
        ...this.config.headersWithAuthorization(),
      },
    })
      .then((response) => Promise.all([response, response.json()]))
      .then(([response, json]) => {
        console.log("Response JSON: " + JSON.stringify(json));
        if (!response.ok) {
          return { success: false, error: json };
        }
        return { success: true, data: json };
      })
      .catch((e) => {
        this.handleError(e);
      });
  }

  async addOrUpdate(payload) {
    if (this.config.tokenExpired()) {
      await this.auth.refreshToken();
    }
    return fetch(
      this.config.CART_URL + "/" + this.auth.token.userId + "/items",
      {
        method: "PUT",
        mode: "cors",
        headers: {
          ...this.config.headersWithAuthorization(),
        },
        body: JSON.stringify(payload),
      }
    )
      .then((response) => Promise.all([response, response.json()]))
      .then(([response, json]) => {
        console.log("Response JSON: " + JSON.stringify(json));
        if (!response.ok) {
          return { success: false, error: json };
        }
        return { success: true, data: json };
      })
      .catch((e) => {
        this.handleError(e);
      });
  }

  async remove(itemId) {
    if (this.config.tokenExpired()) {
      await this.auth.refreshToken();
    }
    return fetch(
      this.config.CART_URL + "/" + this.auth.token.userId + "/items/" + itemId,
      {
        method: "DELETE",
        mode: "cors",
        headers: {
          ...this.config.headersWithAuthorization(),
        },
      }
    )
      .then((response) => {
        if (!response.ok) {
          return { success: false };
        }
        return { success: true };
      })
      .catch((e) => {
        this.handleError(e);
      });
  }

  handleError(error) {
    const err = new Map([
      [TypeError, "There was a problem fetching the response."],
      [SyntaxError, "There was a problem parsing the response."],
      [Error, error.message],
    ]).get(error.constructor);
    console.log(err);
    return err;
  }
}
