import Config from "./Config";

class CustomerClient {
  auth;
  constructor(auth) {
    this.auth = auth;
    this.config = new Config();
  }

  async fetch() {
    if (this.config.tokenExpired()) {
      await this.auth.refreshToken();
    }
    return fetch(this.config.CUSTOMER_URL + "/" + this.auth.token.userId, {
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

export default CustomerClient;
