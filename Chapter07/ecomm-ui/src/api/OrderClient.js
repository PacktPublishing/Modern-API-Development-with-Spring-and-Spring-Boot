import Config from "./Config";

export default class OrderClient {
  auth;
  constructor(auth) {
    this.auth = auth;
    this.config = new Config();
  }

  async fetch() {
    if (this.config.tokenExpired()) {
      await this.auth.refreshToken();
    }
    return fetch(
      this.config.ORDER_URL + "?customerId=" + this.auth.token.userId,
      {
        method: "GET",
        mode: "cors",
        headers: {
          ...this.config.headersWithAuthorization(),
        },
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

  async add(payload) {
    if (this.config.tokenExpired()) {
      await this.auth.refreshToken();
    }
    payload = { ...payload, customerId: this.auth.token.userId };
    return fetch(this.config.ORDER_URL, {
      method: "POST",
      mode: "cors",
      headers: {
        ...this.config.headersWithAuthorization(),
      },
      body: JSON.stringify(payload),
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
