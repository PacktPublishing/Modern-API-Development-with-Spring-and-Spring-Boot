const Button = (props) => {
  let buttonClass =
    " group flex items-center rounded-md font-medium text-sm px-3 py-2";
  let classColor =
    props.color === "green"
      ? "hover:bg-green-200 hover:text-green-700 bg-green-500 text-gray-100 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-400"
      : "hover:bg-red-200 hover:text-red-700 bg-red-500 text-red-100 focus:outline-none";
  classColor = classColor + buttonClass;
  return (
    <button
      type="button"
      className={classColor}
      onClick={props.onClick}
      id={props.id}
      aria-haspopup={props.hasPopup ? "true" : "false"}
    >
      {props.text}
    </button>
  );
};

export default Button;
