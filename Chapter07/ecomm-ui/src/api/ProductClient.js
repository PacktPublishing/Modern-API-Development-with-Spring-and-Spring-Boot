import Config from "./Config";

class ProductClient {
  constructor() {
    this.config = new Config();
  }

  async fetchList() {
    return fetch(this.config.PRODUCT_URL, {
      method: "GET",
      mode: "cors",
      headers: {
        ...this.config.defaultHeaders(),
      },
    })
      .then((response) => Promise.all([response, response.json()]))
      .then(([response, json]) => {
        if (!response.ok) {
          return { success: false, error: json };
        }
        return { success: true, data: json };
      })
      .catch((e) => {
        return this.handleError(e);
      });
  }

  async fetch(prodId) {
    console.log("Fetching product for Id: " + prodId);
    return fetch(this.config.PRODUCT_URL + "/" + prodId, {
      method: "GET",
      mode: "cors",
      headers: {
        ...this.config.defaultHeaders(),
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

export default ProductClient;
